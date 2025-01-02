package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.validation

object ValidationUtils {
    /**
     * Kiểm tra email.
     * - Email không được để trống.
     * - Email phải đúng định dạng.
     * @param email Chuỗi email cần kiểm tra.
     * @return Thông báo xác định email hợp lệ hay không.
     */
    fun validateEmail(email: String): String {
        return if (email.isBlank() || !ValidationRules.isValidEmailFormat(email)) {
            "Email không hợp lệ." // Trả về thông báo nếu email không hợp lệ.
        } else {
            "Email hợp lệ." // Trả về thông báo nếu email hợp lệ.
        }
    }

    /**
     * Kiểm tra mật khẩu.
     * - Đảm bảo mật khẩu tuân thủ các quy tắc:
     *   1. Có độ dài tối thiểu (được định nghĩa trong ValidationRules.MIN_PASSWORD_LENGTH).
     *   2. Chứa ít nhất một chữ số.
     *   3. Chứa ít nhất một chữ cái viết hoa.
     *   4. Chứa ít nhất một chữ cái viết thường.
     *   5. Chứa ít nhất một ký tự đặc biệt.
     * @param password Chuỗi mật khẩu cần kiểm tra.
     * @return Thông báo xác định mật khẩu hợp lệ hay không.
     */
    fun validatePassword(password: String): String {
        if (password.length < ValidationRules.MIN_PASSWORD_LENGTH) {
            return "Mật khẩu phải có ít nhất ${ValidationRules.MIN_PASSWORD_LENGTH} ký tự." // Kiểm tra độ dài.
        }
        if (!password.any { it.isDigit() }) {
            return "Mật khẩu phải chứa ít nhất một số." // Kiểm tra có chữ số hay không.
        }
        if (!password.any { it.isUpperCase() }) {
            return "Mật khẩu phải chứa ít nhất một chữ cái in hoa." // Kiểm tra có chữ in hoa hay không.
        }
        if (!password.any { it.isLowerCase() }) {
            return "Mật khẩu phải chứa ít nhất một chữ cái in thường." // Kiểm tra có chữ in thường hay không.
        }
        if (!ValidationRules.containsSpecialCharacter(password)) {
            return "Mật khẩu phải chứa ít nhất một ký tự đặc biệt." // Kiểm tra có ký tự đặc biệt hay không.
        }
        return "Mật khẩu hợp lệ." // Nếu tất cả điều kiện đều đúng.
    }

    /**
     * Kiểm tra xác nhận lại mật khẩu.
     * - Mật khẩu và mật khẩu xác nhận phải trùng khớp.
     * @param password Mật khẩu gốc.
     * @param confirmPassword Mật khẩu xác nhận.
     * @return Thông báo xác định mật khẩu xác nhận hợp lệ hay không.
     */
    fun validateConfirmPassword(password: String, confirmPassword: String): String {
        return if (password != confirmPassword) {
            "Mật khẩu xác nhận không khớp." // Thông báo nếu mật khẩu xác nhận không trùng.
        } else {
            "Mật khẩu xác nhận hợp lệ." // Thông báo nếu mật khẩu xác nhận hợp lệ.
        }
    }

    /**
     * Kiểm tra họ tên.
     * - Họ tên không được để trống.
     * - Họ tên phải có độ dài tối thiểu (được định nghĩa trong ValidationRules.MIN_FULLNAME_LENGTH).
     * @param fullName Chuỗi họ tên cần kiểm tra.
     * @return Thông báo xác định họ tên hợp lệ hay không.
     */
    fun validateFullName(fullName: String): String {
        return if (fullName.isBlank() || fullName.length < ValidationRules.MIN_FULLNAME_LENGTH) {
            "Họ tên không hợp lệ. Phải có ít nhất ${ValidationRules.MIN_FULLNAME_LENGTH} ký tự." // Kiểm tra độ dài tối thiểu.
        } else {
            "Họ tên hợp lệ." // Nếu hợp lệ.
        }
    }

    /**
     * Kiểm tra địa chỉ.
     * - Địa chỉ không được để trống.
     * - Địa chỉ phải có độ dài tối thiểu (được định nghĩa trong ValidationRules.MIN_ADDRESS_LENGTH).
     * @param address Chuỗi địa chỉ cần kiểm tra.
     * @return Thông báo xác định địa chỉ hợp lệ hay không.
     */
    fun validateAddress(address: String): String {
        return if (address.isBlank() || address.length < ValidationRules.MIN_ADDRESS_LENGTH) {
            "Địa chỉ không hợp lệ. Phải có ít nhất ${ValidationRules.MIN_ADDRESS_LENGTH} ký tự." // Kiểm tra độ dài tối thiểu.
        } else {
            "Địa chỉ hợp lệ." // Nếu hợp lệ.
        }
    }

    /**
     * Kiểm tra số điện thoại.
     * - Số điện thoại không được để trống.
     * - Số điện thoại phải tuân theo định dạng Regex (được định nghĩa trong ValidationRules.PHONE_NUMBER_REGEX).
     * @param phoneNumber Chuỗi số điện thoại cần kiểm tra.
     * @return Thông báo xác định số điện thoại hợp lệ hay không.
     */
    fun validatePhoneNumber(phoneNumber: String): String {
        return if (phoneNumber.isBlank() || !phoneNumber.matches(Regex(ValidationRules.PHONE_NUMBER_REGEX))) {
            "Số điện thoại không hợp lệ. Phải từ 10-15 số." // Kiểm tra Regex và độ dài.
        } else {
            "Số điện thoại hợp lệ." // Nếu hợp lệ.
        }
    }
}
