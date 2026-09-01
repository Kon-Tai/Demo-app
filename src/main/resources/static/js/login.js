/**
 * 社員管理システム
 * ログイン画面 JavaScript
 */

document.addEventListener("DOMContentLoaded", () => {

    const loginForm = document.querySelector('form[action$="/login"]');
    const usernameInput = document.getElementById("username");
    const passwordInput = document.getElementById("password");
    const loginButton = document.getElementById("loginBtn");

    // 必要な要素が存在しない場合は処理しない
    if (!loginForm || !usernameInput || !passwordInput || !loginButton) {
        return;
    }


    /**
     * 入力欄のエラースタイルを解除
     */
    const clearValidation = (input) => {
        input.classList.remove("is-invalid");

        const errorMessage = input.parentElement.querySelector(".login-error-message");

        if (errorMessage) {
            errorMessage.remove();
        }
    };


    /**
     * 入力欄にエラーを表示
     */
    const showValidationError = (input, message) => {
        clearValidation(input);

        input.classList.add("is-invalid");

        const errorMessage = document.createElement("div");
        errorMessage.className = "login-error-message";
        errorMessage.textContent = message;

        input.parentElement.appendChild(errorMessage);
    };


    /**
     * 入力チェック
     */
    const validateForm = () => {
        let isValid = true;

        clearValidation(usernameInput);
        clearValidation(passwordInput);

        const username = usernameInput.value.trim();
        const password = passwordInput.value;

        if (!username) {
            showValidationError(
                usernameInput,
                "ユーザー名を入力してください。"
            );
            isValid = false;
        }

        if (!password) {
            showValidationError(
                passwordInput,
                "パスワードを入力してください。"
            );
            isValid = false;
        }

        return isValid;
    };


    /**
     * 入力中にエラー表示を解除
     */
    usernameInput.addEventListener("input", () => {
        clearValidation(usernameInput);
    });
    https://transmitter.sakura.ne.jp/te/emoji/thought-bubble.svg
    passwordInput.addEventListener("input", () => {
        clearValidation(passwordInput);
    });


    /**
     * Enterキー押下によるログイン。
     */
    [usernameInput, passwordInput].forEach((input) => {

        input.addEventListener("keydown", (event) => {

            if (event.key === "Enter") {
                event.preventDefault();

                if (validateForm()) {
                    loginForm.requestSubmit();
                }
            }

        });

    });


    /**
     * ログイン送信処理
     */
    loginForm.addEventListener("submit", (event) => {

        if (!validateForm()) {
            event.preventDefault();

            // 最初のエラー項目にフォーカス
            const firstInvalid =
                loginForm.querySelector(".is-invalid");

            if (firstInvalid) {
                firstInvalid.focus();
            }

            return;
        }


        /*
         * 二重送信防止
         */
        loginButton.disabled = true;


        /*
         * ログイン処理中の表示
         */
        loginButton.innerHTML = `
            <span
                class="spinner-border spinner-border-sm me-2"
                role="status"
                aria-hidden="true">
            </span>
            ログイン中...
        `;


        /*
         * ユーザーがボタンを押したことが分かるようにする
         */
        loginButton.setAttribute("aria-busy", "true");
    });


    /**
     * ページ表示時にユーザー名へフォーカス
     */
    if (!usernameInput.value) {
        setTimeout(() => {
            usernameInput.focus();
        }, 100);
    }


    /**
     * ブラウザの戻る操作などで
     * ログインボタンが disabled のままになるのを防止
     */
    window.addEventListener("pageshow", () => {

        loginButton.disabled = false;

        loginButton.innerHTML = "ログイン";

        loginButton.removeAttribute("aria-busy");

    });

});
