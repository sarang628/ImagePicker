# 이미지 피커 라이브러리

<img src = "https://github.com/sarang628/ImagePicker/blob/master/screenshot/Screenshot_20201227_231149.png?raw=true" width="25%;" >

## 샘플 코드

```kotlin
class MainActivity : AppCompatActivity() {

    private val imagePicker = ImagePicker.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnImagePick.setOnClickListener {
            imagePicker.actionOpenDocument(this){
                binding.ivBitmap.setImageBitmap(it)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let { imagePicker.onActivityResult(requestCode, resultCode, it, contentResolver) }

    }
}
```


```kotlin
interface ImagePicker {
    fun actionOpenDocument(activity: Activity, onReceiveBitmapListener : (Bitmap) -> Unit)
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent, contentResolver: ContentResolver)

    companion object {
        fun newInstance(): ImagePicker {
            return ImagePickerImpl()
        }
    }
}
```