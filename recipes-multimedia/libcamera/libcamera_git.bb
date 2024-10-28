SUMMARY = "Linux libcamera framework"
SECTION = "libs"

LICENSE = "GPL-2.0-or-later & LGPL-2.1-or-later"

LIC_FILES_CHKSUM = "\
    file://LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c \
    file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68 \
"

SRC_URI = " \
    git://git.libcamera.org/libcamera/libcamera.git;protocol=https;branch=master \
    file://0001-rkisp1-Plumb-the-DW100-dewarper-as-converter.patch \
    file://0002-Add-support-for-IPA-debugging-metadata.patch \
    file://0003-ipa-rkisp1-Honor-FrameDurationLimits.patch \
    "

#SRC_URI = "git://git@gitlab.theimagingsource.com:49107/nxp-imx/libcamera.git;protocol=ssh;branch=arne"

#SRCREV = "aee16c06913422a0ac84ee3217f87a9795e3c2d9"
#SRCREV = "72647e7d851ccac1d88932799c2cb5e8a24bcb11"
SRCREV = "994588fb75838b46d5eb74b916d66f0b9fc49421"


PE = "1"

S = "${WORKDIR}/git"

DEPENDS = "python3-pyyaml-native python3-jinja2-native python3-ply-native python3-jinja2-native udev gnutls chrpath-native libevent libyaml tiff"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'qt', 'qtbase qtbase-native', '', d)}"

PACKAGES =+ "${PN}-gst"
PACKAGES =+ "${PN}-pycamera"

PACKAGECONFIG ??= "gst pycamera"
PACKAGECONFIG[gst] = "-Dgstreamer=enabled,-Dgstreamer=disabled,gstreamer1.0 gstreamer1.0-plugins-base"
PACKAGECONFIG[pycamera] = "-Dpycamera=enabled,-Dpycamera=disabled,python3 python3-pybind11"
PACKAGECONFIG[qcam] = "-Dqcam=enabled,-Dqcam=disabled,qtbase qttools qttools-native"

LIBCAMERA_PIPELINES ??= "rkisp1,uvcvideo"

EXTRA_OEMESON = " \
    -Dpipelines=${LIBCAMERA_PIPELINES} \
    -Dv4l2=true \
    -Dcam=enabled \
    -Dlc-compliance=disabled \
    -Dtest=false \
    -Ddocumentation=disabled \
"

RDEPENDS:${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'wayland qt', 'qtwayland', '', d)}"

inherit meson pkgconfig python3native python3-dir

# do_configure:prepend() {
#     sed -i -e 's|py_compile=True,||' ${S}/utils/ipc/mojo/public/tools/mojom/mojom/generate/template_expander.py
# }

do_install:append() {
    chrpath -d ${D}${libdir}/libcamera.so
    chrpath -d ${D}${libexecdir}/libcamera/v4l2-compat.so
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/libcamera/utils   
    install -D ${S}/src/py/libcamera/utils/* ${D}${PYTHON_SITEPACKAGES_DIR}/libcamera/utils   
}

do_package:append() {
    bb.build.exec_func("do_package_recalculate_ipa_signatures", d)
}

do_package_recalculate_ipa_signatures() {
    local modules
    for module in $(find ${PKGD}/usr/lib/libcamera -name "*.so.sign"); do
        module="${module%.sign}"
        if [ -f "${module}" ] ; then
            modules="${modules} ${module}"
        fi
    done

    ${S}/src/ipa/ipa-sign-install.sh ${B}/src/ipa-priv-key.pem "${modules}"
}

FILES:${PN} += " ${libexecdir}/libcamera/v4l2-compat.so"
FILES:${PN}-gst = "${libdir}/gstreamer-1.0"
FILES:${PN}-pycamera = "${PYTHON_SITEPACKAGES_DIR}/libcamera"

# libcamera-v4l2 explicitly sets _FILE_OFFSET_BITS=32 to get access to
# both 32 and 64 bit file APIs.
GLIBC_64BIT_TIME_FLAGS = ""
