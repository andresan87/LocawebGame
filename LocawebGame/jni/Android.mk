LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := data
LOCAL_SRC_FILES := data.cpp

include $(BUILD_SHARED_LIBRARY)
