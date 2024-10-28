inherit core-image

PV = "1.0"
PR = "r1"


TOOLCHAIN_HOST_TASK:append = " nativesdk-python3-jinja2 nativesdk-python3-ply nativesdk-python3-pyyaml"

TEZI_AUTO_INSTALL = "true"

CONMANPKGS ?= "connman connman-plugin-loopback connman-plugin-ethernet connman-plugin-wifi connman-client"

PREFERRED_VERSION_libcamera = "git"

IMAGE_FEATURES:append = " \
    weston \
    hwcodecs \
    debug-tweaks \
    tools-debug \
"


IMAGE_INSTALL:append = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-ugly \
    libcamera  libcamera-gst \
    force-edid \
    gst-mipi-demo \
    weston-xwayland \
    xterm less \
    python3-pygobject python3-pyserial python3-pyyaml \
    python3-numpy python3-pyqt5 \
    ${CONMANPKGS} packagegroup-basic \
    v4l-utils media-ctl coreutils util-linux i2c-tools python3 python3-smbus bash \
    libgpiod libgpiod-tools python3-gpiod \
    "

