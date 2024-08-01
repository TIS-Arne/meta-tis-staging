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

inherit systemd

SRC_URI = "git://git@gitea.unicap-imaging.org:222/tis/mipi_camera_test.git;protocol=ssh;branch=main"

# Modify these as desired
PV = "1.0+git"
SRCREV = "c391cf3907f31d277e2ef6c1954dfdca585db944"

S = "${WORKDIR}/git"

#SYSTEMD_AUTO_ENABLE = "enable"
#SYSTEMD_SERVICE:${PN} = "autostart-app.service"

DEPENDS = "libcamera"
RDEPENDS:${PN} = "python3-core gtk+3"

# NOTE: no Makefile found, unable to determine what needs to be done

do_configure () {
	# Specify any needed configure commands here
    :
}

do_compile () {
	# Specify compilation commands here
    :
}

do_install () {
	# Specify install commands here
    install -d ${D}/opt/app
    cp ${S}/*.py ${D}/opt/app/
    cp ${S}/*.sh ${D}/opt/app/
    cp ${S}/*.css ${D}/opt/app/
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${S}/autostart-app.service ${D}/${systemd_unitdir}/system
    install -d ${D}/usr/share/libcamera/ipa/rkisp1
    install -m 0444 ${S}/imx290.yaml ${D}/usr/share/libcamera/ipa/rkisp1
    install -m 0444 ${S}/imx296.yaml ${D}/usr/share/libcamera/ipa/rkisp1
}

FILES:${PN} += " \
    /usr/share/libcamera/ipa/rkisp1/imx290.yaml \
    /usr/share/libcamera/ipa/rkisp1/imx296.yaml \
    /opt/app \
    ${systemd_unitdir}/system \
"
