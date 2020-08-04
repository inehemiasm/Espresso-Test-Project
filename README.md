# Espresso Testing 

Espresso is a powerful open source framework to test your native android apps. However, in many cases we need to have more options to customize based on our project needs. In this sample project, i will integrate Spoon (https://github.com/square/spoon). Spoon library allows user to distribute instrumentation test across multiple devices and displaying the results in a meaningful way. Also, in most cases you will need a way to modify your test setup such as locale, tag, suite, etc. In this example i will demonstrate how to pass those values as instrumentation arguments and which can be set through gradle and used in the testing framework.  

REQUIREMENTS

```bash
Android Studio
```

## Usage

```python
# Example test to run test for Brazil locale

.gradlew spoonDebugAndroidTest -Ptitle=Brazil-Test -Plocale=pt_br -Psuite=SMOKE_BR
```

## Contributing
Pull requests are welcome 
