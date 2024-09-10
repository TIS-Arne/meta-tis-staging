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

SRC_URI = "git://git@gitlab.theimagingsource.com:49107/nxp-imx/gst-wrapper.git;protocol=ssh;branch=main"

# Modify these as desired
PV = "1.0+git"
SRCREV = "56f98b1ce9a7a2f90b632d51d193f1dae1720754"

S = "${WORKDIR}/git"

PREFERRED_RPROVIDER_libgstwayland-1.0 = "gstreamer1.0-plugins-base"

RDEPENDS:${PN} = " \
    libgstwayland-1.0 \
    "

DEPENDS = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-bad \
    gtk+3 \
"

inherit meson
inherit vala
inherit pkgconfig

FILES:${PN} = "/usr/lib/gstreamer-1.0/libgstgtkvideosinkwrapper.so"

