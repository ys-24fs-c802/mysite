export const inputValidator = {
    hasWhiteSpace: (str) => /\s/.test(str),
    hasSpecialChar: (str) =>/[!@#$%^&*():{}|<>,'~_=\.\-\\`]/.test(str),
    startWithNumber: (str) => /^[0-9]/.test(str),
    // 결과를 반대로 리턴
    validateLandLineNumber: (str) => !/^(?:\d{2,3}-)?\d{3,4}-\d{4}$/.test(str),
    // 결과를 반대로 리턴
    validateMobileNumber: (str) => !/^01([0|1|6|7|8|9])-?(\d{3,4})-?(\d{4})$/.test(str),
    // 결과를 반대로 리턴
    validateBusinessNumber: (str) => !/^\d{3}-\d{2}-\d{5}$/.test(str)
}

