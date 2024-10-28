FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

do_install:append(){
    install -m 0755 -d ${D}${sysconfdir}/vnc/keys/
    chown weston:weston ${D}${sysconfdir}/vnc/keys/
}

FILES:${PN} += "\
    ${sysconfdir}/vnc/keys \
"
