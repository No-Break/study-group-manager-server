# study-group-manager-server

스터디 그룹 관리를 도와주는 서버입니다.

### 테스트케이스 네이밍 전략
* 컨트롤러
    * \`HTTP 메소드 리소스__구체적인 상태 혹은 조건__HTTP응답상태 반환값\`
    * 예시) \`POST point-histories__to be successful__CREATED historyId\`
* 그외 컴포넌트
    * \`동작할 메소드 이름__구체적인 상태 혹은 조건__예상되는 결과\`
    * 예시) \`main__no args__call run in SpringApplication\`
