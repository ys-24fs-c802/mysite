export class FormValidator {
    constructor(formId, config) {
        this.form = document.getElementById(formId);
        this.config = config;
        this.init();
    }

    init() {
        this.setupValidation();
        this.setupSubmit();
    }


    // 유효성 검사 설정
    setupValidation() {
        Object.values(this.config.fields).forEach(field => {
            const fieldElement = document.getElementById(field.id);
            fieldElement.addEventListener('input', function (e) {
                const value = e.target.value;
                field.rules.forEach(rule => {
                    const errorElement = document.getElementById(rule.errorId);
                    if (rule.validate(value)) {
                        errorElement.style.display =  'block';
                        errorElement.innerText = rule.message;
                    } else {
                        errorElement.style.display =  'none';
                        errorElement.innerText = '';
                    }
                })
            })
        })
    }

    // 폼 전송
    setupSubmit() {
        // 화살쵸 함수를 사용하여 이벤트 핸들러 내부에서 this가 FormValidator 인스턴스를 가리키도록 설정
        this.form.addEventListener('submit', (e) => {
            e.preventDefault();
            // 요청 객체 생성
            const formValues = {};
            let isValid = true;

            // 데이터 수집
            for (const field of Object.values(this.config.fields)) {
                const value = document.getElementById(field.id).value;

                // 유효성 검사
                if (field.rules.some(rule => rule.validate(value))) {
                    isValid = false;
                    break;
                }

                formValues[field.id] = value;
            }

            // 유효성검사 실패시 요청 객체 null로 설정
            if (!isValid) {
                this.claroFormValues(formValues);
                return;
            }

            // 서버로 전송
            console.log(formValues);
        })
    }

    // 폼 값을 초기화
    claroFormValues(formValues) {
        Object.values(formValues).forEach(key => { delete formValues[key] });
    }
}