LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""


SRC_URI = "file://halcon-${PV}-armv7a-linux-hpeek.tar.gz;subdir=hpeek;name=hpeek "
SRC_URI_hpeek[md5sum] = "82a1d91ca9cf134399a3c378a4f38839"
SRC_URI_hpeek[sh256sum]  = "bcac4802f81f0d27e8201af0125c289f157c8c81e30e7fc8446701f637cc4e3d"

S = "${WORKDIR}/hpeek"
B = "${WORKDIR}/build"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
do_package_qa[noexec] = "1"
EXCLUDE_FROM_SHLIBS = "1"


deltask do_compile
deltask do_package_qa


do_install() {
    install -d ${D}/opt/hpeek
    install -d ${D}/etc/udev/rules.d

    echo "#!/bin/bash" > ${D}/opt/hpeek/launcher.sh
    echo "DISPLAY=:0.0" >> ${D}/opt/hpeek/launcher.sh
    echo "cd /opt/hpeek && ./run_overview.sh" >> ${D}/opt/hpeek/launcher.sh
    chmod guo+x ${D}/opt/hpeek/launcher.sh

    cp -R --preserve=mode,timestamps ${S}/* ${D}/opt/hpeek/
    rm -r ${D}/opt/hpeek/bin/armv7a-linux
    rm -r ${D}/opt/hpeek/lib/armv7a-linux
    rm -r ${D}/opt/hpeek/genicam/bin/Linux32_ARMhf

    mv ${D}/opt/hpeek/misc/linux/udev/rules.d/* ${D}/etc/udev/rules.d/

}

FILES:${PN} = "/opt/hpeek/*"
FILES:${PN} += "/etc/udev/rules.d/*"
INSANE_SKIP:${PN} = "ldflags"
