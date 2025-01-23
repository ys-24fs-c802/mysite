import { inputValidator } from "./common.js";

document.getElementById('item_id').addEventListener('input', function(e) {
    const value = e.target.value;
    const spaceError = document.getElementById('spaceError');
    const specialCharError = document.getElementById('specialCharError');
    const startWithNumberError = document.getElementById('startWithNumberError');

    //console.log(value, "=>", hasWhiteSpace(value));

    spaceError.style.display = inputValidator.hasWhiteSpace(value) ? 'block' : 'none';
    specialCharError.style.display = inputValidator.hasSpecialChar(value) ? 'block' : 'none';
    startWithNumberError.style.display = inputValidator.startWithNumber(value) ? 'block' : 'none';
})

document.getElementById('itemForm').addEventListener('submit', function(e) {

    e.preventDefault();

    const item = {
        item: document.getElementById('item_id').value,
    }

    if (!inputValidator.hasWhiteSpace(item.item) &&
        !inputValidator.hasSpecialChar(item.item) &&
        !inputValidator.startWithNumber(item.item)) {
            alert('서버로 전송한다.');
    } else {
        alert('입력값을 다시 확인해주세요.');
    }

    // fetch(요청주소, 요청내용객체)
    // 성공
    // 실
    fetch("/items", {
        method: 'post',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(item)
    }).then(response => {
        if (response.ok) {
            alert('아이템이 성공적으로 생성되었습니다.');
            document.getElementById('itemForm').reset();
        } else {
            alert('아이템 생성에 실패했습니다.');
        }
    }).catch(error => {
        console.error('Error:', error);
        alert('오류가 발생했습니다.');
    });
});