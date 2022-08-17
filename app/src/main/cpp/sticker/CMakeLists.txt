#rlottie base on Telegram 7.9.3
cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_CXX_FLAGS "-std=c++14 -DANDROID")
set(CMAKE_C_FLAGS "-w -std=c11 -DANDROID -D_LARGEFILE_SOURCE=1")
set(CMAKE_ASM_FLAGS "${CFLAGS} -x assembler-with-cpp")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -ffunction-sections -fdata-sections")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -ffunction-sections -fdata-sections")
set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -Wl,--gc-sections -Wl,--exclude-libs,sticker/liblz4.a,sticker/librlottie.a")

if (${ANDROID_ABI} STREQUAL "armeabi-v7a" OR ${ANDROID_ABI} STREQUAL "arm64-v8a")
    enable_language(ASM)
else()
    enable_language(ASM_NASM)
endif()

#rlottie
add_library(rlottie STATIC
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/lottie/lottieanimation.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/lottie/lottieitem.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/lottie/lottiekeypath.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/lottie/lottieloader.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/lottie/lottiemodel.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/lottie/lottieparser.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/lottie/lottieproxymodel.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/freetype/v_ft_math.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/freetype/v_ft_raster.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/freetype/v_ft_stroker.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/pixman/vregion.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/stb/stb_image.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vbezier.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vbitmap.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vbrush.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vcompositionfunctions.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vdasher.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vdebug.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vdrawable.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vdrawhelper.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vdrawhelper_neon.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/velapsedtimer.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vimageloader.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vinterpolator.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vmatrix.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vpainter.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vpath.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vpathmesure.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vraster.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vrect.cpp
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/vrle.cpp)
target_compile_options(rlottie PUBLIC
        -Wall -fno-rtti -finline-functions -ffast-math -Os -fno-unwind-tables -fno-asynchronous-unwind-tables -Wnon-virtual-dtor -Woverloaded-virtual -Wno-unused-parameter -fvisibility=hidden)
set_target_properties(rlottie PROPERTIES
        ANDROID_ARM_MODE arm)
target_compile_definitions(rlottie PUBLIC
        HAVE_PTHREAD NDEBUG)
target_include_directories(rlottie PUBLIC
        ${PROJECT_SOURCE_DIR}/sticker/./
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/inc
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/pixman
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/freetype
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/stb)

if (${ANDROID_ABI} STREQUAL "armeabi-v7a")
    target_compile_options(rlottie PUBLIC
            -fno-integrated-as)
    target_compile_definitions(rlottie PUBLIC
            USE_ARM_NEON)
    target_sources(rlottie PRIVATE
            ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/pixman/pixman-arm-neon-asm.S)
elseif(${ANDROID_ABI} STREQUAL "arm64-v8a")
    target_compile_options(rlottie PUBLIC
            -fno-integrated-as)
    target_compile_definitions(rlottie PUBLIC
            USE_ARM_NEON __ARM64_NEON__)
    target_sources(rlottie PRIVATE
            ${PROJECT_SOURCE_DIR}/sticker/rlottie/src/vector/pixman/pixman-arma64-neon-asm.S)
endif()

#lz4
add_library(lz4 STATIC
        ${PROJECT_SOURCE_DIR}/sticker/lz4/lz4.c
        ${PROJECT_SOURCE_DIR}/sticker/lz4/lz4frame.c
        ${PROJECT_SOURCE_DIR}/sticker/lz4/xxhash.c)
target_compile_options(lz4 PUBLIC
        -w -O3)
set_target_properties(lz4 PROPERTIES
        ANDROID_ARM_MODE arm)

set(NATIVE_LIB "tmessages.40")

#tmessages
add_library(${NATIVE_LIB} SHARED
        ${PROJECT_SOURCE_DIR}/sticker/intro/IntroRenderer.c
        ${PROJECT_SOURCE_DIR}/sticker/jni.c
        ${PROJECT_SOURCE_DIR}/sticker/lottie.cpp)

target_compile_options(${NATIVE_LIB} PUBLIC
        -ffast-math -Os -funroll-loops -ffast-math -fno-strict-aliasing -fno-math-errno)

target_compile_definitions(${NATIVE_LIB} PUBLIC
        BSD=1 NULL=0 SOCKLEN_T=socklen_t ANDROID_NDK DISABLE_IMPORTGL AVOID_TABLES ANDROID_TILE_BASED_DECODE __STDC_CONSTANT_MACROS ANDROID_ARMV6_IDCT OPUS_BUILD FIXED_POINT USE_ALLOCA restrict= __EMX__ LOCALE_NOT_USED HAVE_LRINT HAVE_LRINTF)

if (${ANDROID_ABI} STREQUAL "armeabi-v7a")
    set_target_properties(${NATIVE_LIB} PROPERTIES
            ANDROID_ARM_NEON FALSE
            ANDROID_ARM_MODE arm)
    target_compile_definitions(${NATIVE_LIB} PUBLIC
            ANDROID_ARM_NEON=false)
else()
    set_target_properties(${NATIVE_LIB} PROPERTIES
            ANDROID_ARM_NEON FALSE
            ANDROID_ARM_MODE arm)
endif()

if (${ANDROID_ABI} STREQUAL "armeabi-v7a" OR ${ANDROID_ABI} STREQUAL "arm64-v8a")
    target_compile_definitions(${NATIVE_LIB} PUBLIC
            LIBYUV_NEON OPUS_HAVE_RTCD OPUS_ARM_ASM)

elseif(${ANDROID_ABI} STREQUAL "x86")
    target_compile_definitions(${NATIVE_LIB} PUBLIC
            x86fix)
endif()

target_include_directories(${NATIVE_LIB} PUBLIC
        ${PROJECT_SOURCE_DIR}/sticker/rlottie/inc
        ${PROJECT_SOURCE_DIR}/sticker/lz4)
target_link_libraries(${NATIVE_LIB}
        -Wl,--whole-archive -Wl,--no-whole-archive
        lz4
        rlottie
        jnigraphics
        log
        z
        GLESv2
        EGL
        android
        OpenSLES
        ##cpufeatures
        )

if (${ANDROID_ABI} STREQUAL "x86" OR ${ANDROID_ABI} STREQUAL "x86_64")
    target_link_libraries(${NATIVE_LIB}
            -Wl,--whole-archive -Wl,--no-whole-archive)
endif()

#if (${ANDROID_ABI} STREQUAL "x86" OR ${ANDROID_ABI} STREQUAL "x86_64")
#    target_link_libraries(${NATIVE_LIB}
#            -Wl,--whole-archive vpxasm -Wl,--no-whole-archive
#            c)
#endif()

include(AndroidNdkModules)
#android_ndk_import_module_cpufeatures()