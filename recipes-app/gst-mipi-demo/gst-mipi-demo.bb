# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "git://git@gitlab.theimagingsource.com:49107/nxp-imx/gst-mipi-demo.git;protocol=ssh;branch=main"

# Modify these as desired
PV = "1.0+git"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEPENDS = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-bad \
    json-glib \
    libgee \
    gtk+3 \
"

RDEPENDS:${PN} = " \
    libcamera-gst \
"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "autostart-gst-mipi-demo.service"

inherit meson pkgconfig vala systemd


do_install:append() {
    install -d ${D}/opt/gst-mipi-demo
    mv ${D}/usr/bin/gst-mipi-demo ${D}/opt/gst-mipi-demo/gst-mipi-demo
    mv ${D}/usr/share/gst-mipi-demo/config.json ${D}/opt/gst-mipi-demo/config.json
    cp ${S}/launch-gst-mipi-demo.sh ${D}/opt/gst-mipi-demo/
    rm -r ${D}/usr
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${S}/autostart-gst-mipi-demo.service ${D}/${systemd_unitdir}/system
}

FILES:${PN} = " \
    /opt/gst-mipi-demo \
    ${systemd_unitdir}/system \
    "
