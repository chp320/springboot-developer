// 글 삭제 기능 구현
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/articles');
            });
    });
}


// 수정 기능
// id가 modify-btn인 엘리먼트 조회
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    // 클릭 이벤트 발생하면 수정 api 호출
    modifyButton.addEventListener('click', event => {
        // URL에서 파라미터를 찾아서
        let params = new URLSearchParams(location.search);
        // 파라미터에서 id를 찾는다
        let id = params.get('id');

        // 수정 api를 호출
        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type":"application/json",
            },
            // body 영역에 HTML 로 입력된 데이터를 json 형식으로 변환
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert('수정이 완료되었습니다.');
                location.replace(`/articles/${id}`);
            });
    });
}

// 등록 기능
// id가 create-btn 인 엘리먼트 조회
const createButton = document.getElementById('create-btn');

if (createButton) {
    // 클릭 이벤트가 발생하면 등록 api 호출
    createButton.addEventListener("click", (event) => {
        fetch("/api/articles", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
            }),
        })
            .then( () => {
                alert("등록이 완료되었습니다.");
                location.replace("/articles");
            });
    });
}