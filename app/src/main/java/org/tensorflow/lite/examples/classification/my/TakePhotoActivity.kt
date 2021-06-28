package org.tensorflow.lite.examples.classification.my

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Typeface
import android.media.ImageReader.OnImageAvailableListener
import android.os.Bundle
import android.os.SystemClock
import android.util.Size
import android.util.TypedValue
import android.widget.ImageButton
import android.widget.Toast
import com.iflower.R
import org.tensorflow.lite.examples.classification.CameraActivity
import org.tensorflow.lite.examples.classification.env.BorderedText
import org.tensorflow.lite.examples.classification.env.Logger
import org.tensorflow.lite.examples.classification.tflite.Classifier
import org.tensorflow.lite.examples.classification.tflite.Classifier.Device
import org.tensorflow.lite.examples.classification.tflite.Classifier.Model
import java.io.IOException



class TakePhotoActivity : CameraActivity(), OnImageAvailableListener {
    private var rgbFrameBitmap: Bitmap? = null
    private var sensorOrientation: Int? = null
    private var classifier: Classifier? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        findViewById<ImageButton>(R.id.btn_take_photo).setOnClickListener { openResultActivity() }
    }

    override fun getLayoutId() = R.layout.take_photo_fragment
    override fun getDesiredPreviewFrameSize() = DESIRED_PREVIEW_SIZE

    public override fun onPreviewSizeChosen(size: Size, rotation: Int) {
        val textSizePx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, resources.displayMetrics
        )
        val borderedText = BorderedText(textSizePx)
        borderedText.setTypeface(Typeface.MONOSPACE)
        recreateClassifier(model, device, numThreads)
        if (classifier == null) {
            LOGGER.e("No classifier on preview!")
            return
        }
        previewWidth = size.width
        previewHeight = size.height
        sensorOrientation = rotation - screenOrientation
        LOGGER.i("Camera orientation relative to screen canvas: %d", sensorOrientation)
        LOGGER.i("Initializing at size %dx%d", previewWidth, previewHeight)
        rgbFrameBitmap = Bitmap.createBitmap(previewWidth, previewHeight, Bitmap.Config.ARGB_8888)
    }

    override fun processImage() {
        rgbFrameBitmap?.setPixels(rgbBytes, 0, previewWidth, 0, 0, previewWidth, previewHeight)
        runInBackground {
            if (classifier != null) {
                SystemClock.uptimeMillis()
                val results = classifier!!.recognizeImage(rgbFrameBitmap, sensorOrientation!!)
                LOGGER.v("Detect: %s", results)
                runOnUiThread { showResultsInBottomSheet(results) }
            }
            readyForNextImage()
        }
    }

    private fun openResultActivity() {
        rgbFrameBitmap?.setPixels(rgbBytes, 0, previewWidth, 0, 0, previewWidth, previewHeight)
        runInBackground {
            if (classifier != null) {
                SystemClock.uptimeMillis()
                val results = classifier!!.recognizeImage(rgbFrameBitmap, sensorOrientation!!)
                LOGGER.v("Detect: %s", results)
                runOnUiThread {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("result", results[0].title)
                    startActivity(intent)
                    finish()
                }
            }
            readyForNextImage()
        }
    }

    private fun recreateClassifier(model: Model, device: Device, numThreads: Int) {
        if (classifier != null) {
            LOGGER.d("Closing classifier.")
            classifier?.close()
            classifier = null
        }
        try {
            LOGGER.d("Creating classifier (model=%s, device=%s, numThreads=%d)",
                model, device, numThreads
            )
            classifier = Classifier.create(this, model, device, numThreads)
        } catch (e: IOException) {
            LOGGER.e(e, "Failed to create classifier.")
            runOnUiThread { Toast.makeText(this, e.message, Toast.LENGTH_LONG).show() }
        } catch (e: IllegalArgumentException) {
            LOGGER.e(e, "Failed to create classifier.")
            runOnUiThread { Toast.makeText(this, e.message, Toast.LENGTH_LONG).show() }
        }
    }

    companion object {
        private val LOGGER = Logger()
        private val DESIRED_PREVIEW_SIZE = Size(640, 480)
        private const val TEXT_SIZE_DIP = 10f
    }
}
