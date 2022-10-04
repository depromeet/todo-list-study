# REAL TODO-LIST
> REST 컨벤션 준수하기

#구현된 기능

##dto
- Request
  - RequestUserDto
  - RequestTodoDto
- Response
    - ResponseUserDto
    - ResponseTodoDto

##controller
- UserController
  - 생성 (method : POST, "/users")
    - RequestBody : RequestUserDto
  - 조회 (method : GET, "/users/{name}")
  - 삭제 (method : DELETE, "/users/{name}")
- TodoController
  - 생성  (method : POST, "/users/{name}/todo-list")
    - RequestBody : RequestTodoDto
  - 목록 조회 (method : GET, "/users/{name}/todo-list")
  - 단일 조회 (method : GET, "/users/{name}/todo-list/{todoId}")
  - 수정 (method : PATCH, "/users/{name}/todo-list/{todoId}")
    - RequestBody : RequestTodoDto
  - 삭제 (method : DELETE, "/users/{name}/todo-list/{todoId}")

##service
- UserService
  - findUser (사용자 검색)
  - createUser (사용자 추가)
  - deleteUser (사용자 삭제)
- TodoService
  - findTodo (할 일 검색)
  - addTodo (할 일 추가)
  - updateTodoTitle (할 일 수정) -- need help
  - deleteTodo (할 일 삭제)
  - isUserContainsTodo (해당 사용자 todo 목록에 있는지)

##exception
- DuplicatedUserException (중복된 사용자 예외)
- TodoNotFoundException (할 일 없는 예외)
- UserNotFoundException (사용자 없는 예외)


# NEED HELP
- TodoService :  List<Todo> -> List<ResponseTodoDto> 간단히.
- todo Patch가 적용되지 않음.