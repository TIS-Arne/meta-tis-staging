FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://tis-sensor-configuration.cfg \
    file://0004-Add-trigger-control-to-imx296.patch \
    file://0003-Use-TIS-default-register-list-for-imx296-initializat.patch \
"

LINUX_REPO = "git://gitlab.com/ideasonboard/nxp/linux.git"
LINUX_VERSION = "6.10"
KBRANCH = "v6.10/dt/imx8mp"

# LINUX_REPO = "git://git.kernel.org/pub/scm/linux/kernel/git/pinchartl/linux.git"
# LINUX_VERSION = "6.10"
# KBRANCH = "next/media/rkisp1"

