FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Set-full-hd-mode-in-the-tdxarg.patch"

# Default u-boot-toradex_mainline uses AUTOREV
SRCREV = "1630ff26cc960439b5949b80cfc604a2c8aa47dd"

