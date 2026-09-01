const menuButton =
    document.getElementById("menuButton");

const sidebarOverlay =
    document.getElementById("sidebarOverlay");


// メニュー開閉
menuButton.addEventListener("click", function () {

    const isOpen =
        document.body.classList.toggle("menu-open");

    menuButton.setAttribute(
        "aria-expanded",
        isOpen
    );

    menuButton.setAttribute(
        "aria-label",
        isOpen
            ? "メニューを閉じる"
            : "メニューを開く"
    );

});


// オーバーレイクリック
sidebarOverlay.addEventListener("click", function () {

    document.body.classList.remove("menu-open");

    menuButton.setAttribute(
        "aria-expanded",
        "false"
    );

    menuButton.setAttribute(
        "aria-label",
        "メニューを開く"
    );

});


// ESCキー
document.addEventListener("keydown", function (event) {

    if (event.key === "Escape") {

        document.body.classList.remove("menu-open");

        menuButton.setAttribute(
            "aria-expanded",
            "false"
        );

        menuButton.setAttribute(
            "aria-label",
            "メニューを開く"
        );

    }

});