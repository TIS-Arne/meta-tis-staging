FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PACKAGECONFIG:append = " rdp vnc headless"

FILES:${PN}:append = " ${sysconfdir}/pam.d/weston-remote-access"
