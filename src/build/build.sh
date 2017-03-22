#!/usr/bin/env bash
# dev 环境快速启动

THIS_DIR=$(dirname "$0")
pushd "$THIS_DIR"

npm run build

# npm run copy:bundle & npm run build:small & npm run build:android

popd