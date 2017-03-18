#!/usr/bin/env bash
# dev 环境快速启动

THIS_DIR=$(dirname "$0")
pushd "$THIS_DIR"

npm run build

#npm run copy:bundle & npm run build:small & npm run build:android

#if [ npm run copy:bundle ]
#then
#    echo 'copy bundle.json 完成'
#     if [android/buildSmall.sh]
#     then
#        echo 'build android small 完成'
#        if [android/buildRelease.sh]
#        then
#            'build android release apk 完成'
#        fi
#    fi
#fi

popd