# gradle run dev

THIS_DIR=$(dirname "$0")
pushd "$THIS_DIR"

./gradlew assemblesyswinRelease

# adb install app/build/outputs/apk/app-syswin-release.apk

popd