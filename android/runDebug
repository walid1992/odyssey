# gradle run dev

THIS_DIR=$(dirname "$0")
pushd "$THIS_DIR"

./gradlew assemblesyswinDebug

adb install app/build/outputs/apk/app-syswin-debug.apk

popd