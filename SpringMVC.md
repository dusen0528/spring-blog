**`Spring MVC` 란?**

- Spring Framework이 직접 제공하는 Servlet API 기반의 웹 프레임워크

**MVC Pattern ( MVC 기반 Model2 )**

- Model + View + Controller

![image.png](attachment:9406c36c-6a71-4fb4-83ab-9fa1163b1192:image.png)

**`Model`**

- 비즈니스 로직 및 데이터를 표현하는 객체입니다.
- 데이터베이스와 상호작용하거나, 사용자의 요청에 대한 결과를 담고 있는 객체들입니다.
- 예를 들어, 데이터베이스에서 값을 가져와서 클라이언트에게 제공하는 역할을 합니다.

**`View`**

- 사용자에게 표시되는 UI(User Interface)를 담당합니다.
- `Thymeleaf` 같은 템플릿 엔진을 사용하여 데이터를 표시합니다.
- View는 모델의 데이터를 받아와서 사용자에게 적합한 형태로 출력합니다.

**`Controller`**

- 클라이언트의 요청을 처리하고, 비즈니스 로직을 호출하여 데이터를 모델로 전달하거나 적절한 뷰를 반환하는 역할을 합니다.
- 클라이언트가 보낸 HTTP 요청을 받고 이를 처리할 비즈니스 로직을 호출한 뒤, 그 결과를 View에 전달하여 응답을 생성합니다.

**Spring MVC의 주요 구성 요소**

**DispatcherServlet**

- Spring MVC Framework의 중심이 되는 Servlet
- Controller로 향하는 모든 웹 요청의 entry point
- Front Controller 디자인 패턴의 표현

![image.png](attachment:b3693f35-a6e0-4c83-869c-89ec51c15b41:image.png)

**HandlerMapping**

- 요청 URL을 받아서 이를 처리할 핸들러(컨트롤러)를 매핑합니다.
- 요청 URL에 적합한 컨트롤러 메서드를 찾는 역할을 합니다.

**Controller**

- 실제로 비즈니스 로직을 수행하고, 모델 데이터를 반환하거나 뷰를 리턴하는 객체입니다.
- 메서드에 `@RequestMapping` 과 같은 애너테이션을 사용하여 요청과 매핑합니다.

**ModelAndView**

- 컨트롤러가 반환하는 객체로, 모델 데이터와 뷰 이름을 함께 담고 있습니다.
- 모델은 데이터를, 뷰는 클라이언트에게 보여질 화면을 나타냅니다.

**ViewResolver**

- 논리적인 뷰 이름을 실제 뷰 구현체로 변환하는 역할을 합니다.
- 예를 들어, `Thymeleaf`, `FreeMarker`, `Groovy`, `Mustache` 등을 사용할 수 있습니다.

---

## **Context Hierarchy (컨텍스트 계층)**

- Root WebApplicationContext에는 공통으로 사용되는 빈들이 포함되고, 각 DispatcherServlet은 자신만의 자식 WebApplicationContext를 가질 수 있습니다. 자식 컨텍스트는 루트 컨텍스트에서 제공하는 빈들을 상속하면서도 필요에 따라 다른 빈을 재정의할 수 있습니다.

![image.png](attachment:7c6eb6b0-49cf-4f6b-9ff9-805031986270:image.png)

**Root WebApplicationContext**

- Root WebApplicationContext는 웹 애플리케이션의 루트 컨텍스트로, 전체 애플리케이션에서 공통으로 사용되는 빈들을 관리합니다. 이 컨텍스트는 보통 웹 애플리케이션의 데이터 계층, 비즈니스 서비스, 공통 설정 빈들을 포함하고, 이 빈들은 여러 개의 DispatcherServlet 인스턴스에서 공유됩니다.
    - @Service
    - @Repository
    - @Configuration

**Servlet WebApplicationContext**

- WebApplicationContext는 ApplicationContext의 서브인터페이스로, 주로 서블릿 환경에서 사용됩니다. 이 컨텍스트는 서블릿 애플리케이션에서 필요한 웹 관련 빈들을 관리하며, **ServletContext**와 결합하여 웹 애플리케이션의 라이프사이클을 관리합니다.
- Spring에서는 DispatcherServlet이 WebApplicationContext와 연결되어 요청을 처리하는 역할을 합니다. WebApplicationContext는 일반적으로 ApplicationContext의 기본 설정을 제공하지만, 추가적으로 서블릿 컨텍스트와의 연결, 웹 리소스 접근, HTTP 요청과 응답 처리 등의 웹 환경에 맞는 기능을 제공합니다.
    - Controller
    - Interceptor

---

## **`@Controller`**

Table of Contents

- [@RequestMapping](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#requestmapping)
- [HTTP 메서드에 특화된 Annotation](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#http-%EB%A9%94%EC%84%9C%EB%93%9C%EC%97%90-%ED%8A%B9%ED%99%94%EB%90%9C-annotation)
    - [@GetMapping()](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#getmapping)
    - [@PostMapping](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#postmapping)
    - [@PutMapping](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#putmapping)
    - [@DeleteMapping](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#deletemapping)
    - [@PatchMapping](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#patchmapping)
- [Controller의 반환 값](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#controller%EC%9D%98-%EB%B0%98%ED%99%98-%EA%B0%92)
- [Ant Pattern을 활용한 `/member` 경로 예제](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#ant-pattern%EC%9D%84-%ED%99%9C%EC%9A%A9%ED%95%9C-member-%EA%B2%BD%EB%A1%9C-%EC%98%88%EC%A0%9C)
    - [1. `/member/` 기본 경로 매핑](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#1-member-%EA%B8%B0%EB%B3%B8-%EA%B2%BD%EB%A1%9C-%EB%A7%A4%ED%95%91)
    - [2. `/member/*` 와일드카드를 이용한 한 부분 매핑](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#2-member-%EC%99%80%EC%9D%BC%EB%93%9C%EC%B9%B4%EB%93%9C%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%ED%95%9C-%EB%B6%80%EB%B6%84-%EB%A7%A4%ED%95%91)
    - [3. `/member/**` 다중 경로 매핑 (하위 경로 포함)](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#3-member-%EB%8B%A4%EC%A4%91-%EA%B2%BD%EB%A1%9C-%EB%A7%A4%ED%95%91-%ED%95%98%EC%9C%84-%EA%B2%BD%EB%A1%9C-%ED%8F%AC%ED%95%A8)
    - [4. `/member/{id}` 경로에서 경로 변수 사용](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#4-memberid-%EA%B2%BD%EB%A1%9C%EC%97%90%EC%84%9C-%EA%B2%BD%EB%A1%9C-%EB%B3%80%EC%88%98-%EC%82%AC%EC%9A%A9)
    - [5. `/member/{id}/profile` 과 같은 경로에서 세부 처리](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#5-memberidprofile-%EA%B3%BC-%EA%B0%99%EC%9D%80-%EA%B2%BD%EB%A1%9C%EC%97%90%EC%84%9C-%EC%84%B8%EB%B6%80-%EC%B2%98%EB%A6%AC)
    - [6. `?` 매칭 예제](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#6-%EB%A7%A4%EC%B9%AD-%EC%98%88%EC%A0%9C)
- [정리](https://github.com/nhnacademy-bootcamp/spring-blog/blob/main/docs/07.spring-mvc/04.controller.adoc#%EC%A0%95%EB%A6%AC)
- `Controller Mapping` 은 HTTP 요청을 특정한 핸들러 메서드(컨트롤러 메서드)와 매핑하는 작업을 의미합니다. 즉, 사용자가 요청한 URL에 맞는 메서드를 호출하여 응답을 생성하게 됩니다.
- Spring에서는 `@RequestMapping` 과 그 파생된 여러 어노테이션을 사용하여 URL 패턴을 특정 메서드에 매핑합니다.

**@RequestMapping**

- 가장 기본적이고 범용적인 어노테이션으로 HTTP 요청을 메서드에 매핑합니다.

```
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = {"/", "/index.do"})
    public String index() {
        return "index/index";
    }
}
```

**HTTP 메서드에 특화된 Annotation**

**@GetMapping()**

- HTTP GET 요청을 처리합니다.

**@PostMapping**

- HTTP POST 요청을 처리합니다.

**@PutMapping**

- HTTP PUT 요청을 처리합니다.

**@DeleteMapping**

- HTTP DELETE 요청을 처리합니다.

**@PatchMapping**

- HTTP PATCH 요청을 처리합니다.

**Controller의 반환 값**

- 기본: viewName 반환
    - 예) `index/index`
    - 예) `member/login`

**Ant Pattern을 활용한 `/member` 경로 예제**

- Spring MVC에서 Ant Pattern을 활용하여 `/member` 경로를 기준으로 다양한 URL 매핑을 처리하는 예제를 설명합니다.
- **Ant Style Pattern**
    - `?` : 1글자 매칭
    - `` : 0글자 이상 매칭
    - `*` : 0글자 이상 하위 경로 매칭

**1. `/member/` 기본 경로 매핑**

- `/member/` 경로에 대해 기본적인 URL을 처리합니다. 예를 들어, `/member/profile`, `/member/settings` 등의 경로를 처리합니다.

```
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")  // "/member" 경로에 대한 기본 매핑
public class MemberController {

    @RequestMapping("/profile")  // "/member/profile" 경로에 매핑
    public String profile() {
        return "profile";  // "profile" 뷰 반환
    }

    @RequestMapping("/settings")  // "/member/settings" 경로에 매핑
    public String settings() {
        return "settings";  // "settings" 뷰 반환
    }
}
```

**2. `/member/*` 와일드카드를 이용한 한 부분 매핑**

- `/member/` 뒤에 오는 한 경로 부분을 처리합니다. 예를 들어, `/member/edit` 와 같은 경로를 처리할 수 있습니다.

```
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberWildcardController {

    @RequestMapping("/member/*")  // "/member/{something}" 경로에 매핑 (하나의 경로 부분만 매칭)
    public String memberGeneral() {
        return "memberGeneral";  // "/member/{something}" 경로에 대한 공통 처리
    }
}
```

- ``는 `/member/` 뒤의 한 부분만을 매칭하므로 `/member/{something}` 형태로 요청이 들어올 때마다 `memberGeneral()` 메서드가 실행됩니다.

**3. `/member/**` 다중 경로 매핑 (하위 경로 포함)**

- `/member/` 경로 하위의 모든 경로를 처리합니다. 예를 들어, `/member/details/123`, `/member/orders/456` 등의 경로를 처리합니다.

```
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberCatchAllController {

    @RequestMapping("/member/**")  // "/member/**" 경로에 대한 매핑 (하위 경로 모두 처리)
    public String catchAll() {
        return "catchAll";  // "/member" 하위의 모든 경로에 대한 공통 처리
    }
}
```

- `/member/details/123` → `catchAll()` 메서드 실행
- `/member/orders/456` → `catchAll()` 메서드 실행
- `/member/123/profile/1` → `catchAll()` 메서드 실행
- `*`는 `/member/` 하위에 모든 경로를 처리하므로 `catchAll()` 메서드가 실행됩니다.

**4. `/member/{id}` 경로에서 경로 변수 사용**

```
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberPathVariableController {

    @RequestMapping("/member/{id}")  // "/member/{id}" 경로에 매핑
    public String memberDetails(@PathVariable String id) {
        return "Member details for ID: " + id;  // {id}를 동적으로 받아서 처리
    }
}
```

- `@PathVariable` 을 사용하여 경로 변수 `{id}` 를 동적으로 처리할 수 있습니다.
    - 예) `/member/123`
    - 예) `/member/abc`

**5. `/member/{id}/profile` 과 같은 경로에서 세부 처리**

- `/member/{id}/profile` 과 같은 경로에서 `id` 를 처리하고 세부 경로인 `/profile` 도 처리합니다.

```
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberProfileController {

    @RequestMapping("/member/{id}/profile")  // "/member/{id}/profile" 경로에 매핑
    public String memberProfile(@PathVariable String id) {
        return "Profile of member with ID: " + id;  // {id}를 동적으로 받아서 처리
    }
}
```

- `{id}` 와 같은 동적인 경로를 처리할 수 있습니다.
    - `/member/123/profile`
    - `/member/abc/profile`

**6. `?` 매칭 예제**

- `?` 는 한 글자만 매칭하는 패턴입니다. 예를 들어, `/member/a` 와 같은 경로에 대해서만 매칭됩니다.

```
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberQuestionMarkController {

    @RequestMapping("/member/?")  // "/member/{singleCharacter}" 경로에 매핑
    public String memberOneChar() {
        return "memberOneChar";  // "/member/{singleCharacter}" 경로에 대한 처리
    }
}
```

- `/member/a` → `memberOneChar()` 메서드 실행
- `/member/1` → `memberOneChar()` 메서드 실행
- `/member/abc` → 매칭되지 않음 (하나의 글자만 매칭)

**정리**

- `/member/*`
    - `/member/` 뒤에 오는 한 부분만 매칭. 예) `/member/edit`, `/member/delete` 등을 처리할 수 있습니다.
- `/member/**`
    - `/member/` 하위에 모든 경로를 매칭. 예) `/member/details/123`, `/member/orders/456` 등을 처리할 수 있습니다.
- `/member/{id}`
    - URL 경로에 포함된 변수를 동적으로 처리. 예) `/member/123`, `/member/abc` 등을 처리할 수 있습니다.
- `/member/{id}/profile`
    - 경로에 포함된 동적 변수와 정적인 서브 경로를 함께 처리. 예) `/member/123/profile` 등을 처리할 수 있습니다.
- `/member/?`
    - 한 글자만 매칭. 예) `/member/a`, `/member/1` 등을 처리할 수 있습니다.

이와 같은 방식으로 `Ant Pattern`을 활용하여 Spring MVC에서 다양한 URL 경로를 유연하게 처리할 수 있습니다.

---

## Login 구현하기

```java

    /**
     * TODO#1 - /login.do <-- get mapping 설정 합니다.
     *  - 로그인이 성공하면 LoginMember 객체가 session에 등록 됩니다.
     *  - 즉 session에 loginMember 객체가 session에 존재 한다면 로그인된 상태 입니다.
     *  - @SessionAttribute(required = false) LoginMember loginMember <-- sesstion으로 부터 loginMember 객체를 얻습니다.
     *  - required = false임으로 loginMember가 session에 존재하지 않다면 null 입니다.
     *  - HttpSession session을 통해서 session에 직접 접근할 수도 있습니다.
     */

    @GetMapping("/login.do")
    public String login(Model model,@SessionAttribute(required = false) LoginMember loginMember) {
        // loginMember 객체가 존재하지 않다면 /index.do redirection 합니다.
        if(Objects.isNull(loginMember)){
            return "redirect:/index.do";
        }
        //viewName = 'member/login'
        return "member/login";
    }

```

- 로그인 페이지 표시하는 메소드
- 세션에서 loginMember 객체를 가져옴
- required=false 는 세션에 해당 객체가 없어도 예외를 발생 시키지 않고 null 발생
- 로그인된 사용자가 없으면 index.do로 리다이렉션
    - 이미 로그인한 사용자가 다시 로그인 페이지에 접근하는 것을 방지
- 로그인 페이지 뷰의 이름을 반환

```java
/**
     * TODO#3 - /loginAction.do post mapping 합니다.
     * - HttpSession을 이용해서 session에 loginMember를 등록 합니다. , session.setMaxInactiveInterval(sec)
     * - 30분동안 session이 유지 될 수 있도록 설정 합니다.
     */

    @PostMapping("/loginAction.do")
    public String loginAction(LoginRequest loginRequest, HttpSession session) {

        //memberService.doLogin 메서드를 호출해 로그인 합니다.
        LoginMember loginMember = memberService.doLogin(loginRequest.getMbEmail(), loginRequest.getMbPassword());
        log.debug("loginAction: {}", loginMember);

        //session에 loginMember등록
        session.setAttribute("loginMember", loginMember);

        //30분 session 유지

        session.setMaxInactiveInterval(30*60);

        //viewName = /index.do redirect 합니다.
        return "redirect:/index.do";
    }
```

- **`session.setAttribute("loginMember", loginMember)`**:
    - 로그인 성공 시 세션에 사용자 정보를 저장
- 로그인 후 메인 페이지로 리다이렉트

```java

    /**
     * TODO#10 /logoutAction.do <- post mapping
     */
    @PostMapping("/loginAction.do")
    public String logoutAction(HttpSession session) {

        //로그인이 되어있지 않다면 UnauthorizedException() 발생 합니다.
        if(Objects.isNull(session)){
            throw new UnauthorizedException("loginMember is not found . do login");
        }
        //현재 사용자의 세션을 완전히 무효화 합니다. session.invalidate() 호출 합니다.

        session.invalidate();

        //viewName=/index.do redirect 합니다.
        return "redirect:/index.do";
    }
```

-
```java

    /**
     * TODO#10 /logoutAction.do <- post mapping
     */
    @PostMapping("/loginAction.do")
    public String logoutAction(HttpSession session) {

        //로그인이 되어있지 않다면 UnauthorizedException() 발생 합니다.
        if(Objects.isNull(session)){
            throw new UnauthorizedException("loginMember is not found . do login");
        }
        //현재 사용자의 세션을 완전히 무효화 합니다. session.invalidate() 호출 합니다.

        session.invalidate();

        //viewName=/index.do redirect 합니다.
        return "redirect:/index.do";
    }
```

- 

---

# HandlerInterceptor

1. **문지기 역할**: 이 코드는 '인터셉터'라는 문지기 역할을 합니다. 누군가가 웹사이트의 특정 페이지에 들어가려고 할 때, 이 문지기가 먼저 확인을 합니다.
2. **로그인 확인**: 문지기는 방문자가 로그인했는지 확인합니다. 이를 위해 '세션'이라는 것을 확인합니다. 세션은 방문자가 로그인했다는 증거 같은 것입니다.
3. **로그인 안 했다면**: 만약 방문자가 로그인하지 않았다면, 문지기는 그 사람을 메인 페이지("/index.do")로 돌려보냅니다. 이것은 "로그인 먼저 하세요"라고 말하는 것과 같습니다.
4. **로그인 했다면**: 방문자가 로그인한 상태라면, 문지기는 "true"를 반환하며 방문자가 원하는 페이지로 갈 수 있게 합니다.

이렇게 이 코드는 웹사이트의 보안을 지키고, 로그인한 사용자만 특정 페이지를 볼 수 있도록 하는 간단하지만 중요한 역할을 합니다.

## **preHandle의 역할**

1. **사전 점검**: 누군가가 웹사이트의 특정 페이지를 보려고 할 때, 그 요청이 실제로 처리되기 전에 preHandle이 먼저 동작합니다.
2. **결정권**: preHandle은 그 요청을 계속 진행할지 아니면 멈출지를 결정합니다.
3. **반환 값의 의미**:
    - true를 반환하면: "괜찮아, 이 요청을 계속 처리해도 돼"라는 뜻입니다.
    - false를 반환하면: "안돼, 이 요청은 여기서 멈춰"라는 뜻입니다.
4. **실행 시점**: 컨트롤러(웹사이트의 주요 처리를 담당하는 부분)가 동작하기 전에 실행됩니다.

## **postHandle 메서드**

1. **역할**: 주문이 준비되고 손님에게 전달되기 직전에 마지막 점검을 하는 것과 비슷해요.
2. **실행 시점**: 웹사이트의 주요 작업(컨트롤러)이 끝난 후, 하지만 결과를 보여주기(뷰 렌더링) 전에 실행돼요.
3. **사용 예**:
    - 페이지에 보여줄 추가 정보를 넣을 수 있어요.
    - 결과를 마지막으로 수정할 수 있어요.
4. **로그 작성**: 이 부분이 잘 동작하는지 확인하기 위해 로그를 남기라고 하네요.

## **afterCompletion 메서드**

1. **역할**: 모든 일이 끝난 후 마지막 정리를 하는 것과 같아요.
2. **실행 시점**: 웹사이트의 모든 작업이 끝나고, 결과까지 다 보여준 후에 실행돼요.
3. **사용 예**:
    - 사용한 자원을 정리할 수 있어요.
    - 오류가 있었는지 최종 확인할 수 있어요.
    - 전체 처리 시간을 측정할 수 있어요.
4. **로그 작성**: 이 부분도 잘 동작하는지 확인하기 위해 로그를 남기라고 해요.

| 특성 | preHandle | postHandle | afterCompletion |
| --- | --- | --- | --- |
| 실행 시점 | 컨트롤러 실행 전 | 컨트롤러 실행 후, 뷰 렌더링 전 | 뷰 렌더링 후 |
| 주요 용도 | 사전 검증, 권한 체크 | 모델 데이터 수정, 추가 | 리소스 정리, 로깅 |
| 요청 중단 가능 | 가능 (false 반환 시) | 불가능 | 불가능 |
| 예외 처리 | 가능 | 가능 | 가능 (다른 메서드의 예외도 처리) |
| 모델 접근 | 불가능 | 가능 | 불가능 |
| 응답 수정 | 가능 | 제한적 | 불가능 |

---

```java
package com.nhnacademy.blog.common.config;

import com.nhnacademy.blog.common.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO#5 - WebMvcConfigurer
 * Spring MVC에서 WebMvcConfigurer는 웹 애플리케이션의 MVC 구성 요소를 커스터마이징하기 위한 인터페이스입니다.
 * 이를 구현하면 Spring MVC의 기본 설정을 확장하거나 사용자 정의 로직을 추가할 수 있습니다.
 * WebMvcConfigurer는 Spring Boot와 Spring Framework의 자동 설정을 변경하거나 추가 설정을 적용하는 데 사용됩니다.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //TODO#6 - registry에 LoginCheckInterceptor등록

                //registry에 addInterceptor() 메서드를 이용해서 LoginCheckInterceptor를 추가 합니다.
                registry.addInterceptor(new LoginCheckInterceptor())

                //.addPathPatterns() method를 사용해서 /member/myinfo.do, /member/logout.do 지정하세요.
                // /member/login.do <-- 로그인 form 임으로 로그인한 사용자도 접근할 수 있음으로  개별적으로 지정 합니다.
                        .addPathPatterns("/member/myinfo.do","/member/logout.do")
                //.excludePathPatterns()는 제외할 uri를 설정할 수 있습니다.

                // ex) /index.do, /member/login.do
                        .excludePathPatterns("/index.do", "/member/login.do");
    }

}
```

## **웹사이트의 교통 정리**

이 코드는 웹사이트의 '교통 정리'를 담당합니다. 마치 도로에서 신호등과 교통 표지판이 차들의 이동을 관리하는 것처럼, 이 코드는 웹사이트에서 사용자들의 이동을 관리합니다.

## **주요 기능**

1. **문지기 배치**: **`LoginCheckInterceptor`**라는 문지기를 웹사이트의 특정 장소에 배치합니다.
2. **통행 규칙 설정**:
    - 어떤 페이지에 문지기를 세울지 정합니다 (**`addPathPatterns()`**).
    - 어떤 페이지는 문지기 없이 자유롭게 다닐 수 있게 합니다 (**`excludePathPatterns()`**).

## **코드 작성 방법**

1. **`registry.addInterceptor(new LoginCheckInterceptor())`**로 문지기를 등록합니다.
2. **`.addPathPatterns("/member/myinfo.do", "/member/logout.do")`**로 문지기가 지켜야 할 페이지를 정합니다.
3. **`.excludePathPatterns("/index.do", "/member/login.do")`**로 문지기 검사 없이 갈 수 있는 페이지를 정합니다.

```java
WebConfig 클래스의 주요 기능
MVC 구성 커스터마이징:

이 클래스는 WebMvcConfigurer 인터페이스를 구현합니다.

이를 통해 Spring MVC의 기본 설정을 확장하거나 사용자 정의 로직을 추가할 수 있습니다.

인터셉터 등록:

addInterceptors 메서드를 오버라이드하여 인터셉터를 등록합니다.

여기서는 LoginCheckInterceptor를 등록하고 있습니다.

URL 패턴 지정:

특정 URL 패턴에 대해 인터셉터를 적용하거나 제외할 수 있습니다.

.addPathPatterns()로 인터셉터를 적용할 URL을 지정합니다.

.excludePathPatterns()로 인터셉터를 적용하지 않을 URL을 지정합니다.

구체적인 설정 내용
로그인 체크 인터셉터 등록:

new LoginCheckInterceptor()를 통해 로그인 체크 인터셉터를 생성하고 등록합니다.

인터셉터 적용 경로 설정:

/member/myinfo.do와 /member/logout.do 경로에 인터셉터를 적용합니다.

이 경로들은 로그인한 사용자만 접근할 수 있어야 하는 페이지들입니다.

인터셉터 제외 경로 설정:

/index.do와 /member/login.do 경로는 인터셉터 적용에서 제외됩니다.

이 경로들은 로그인하지 않은 사용자도 접근할 수 있어야 하는 페이지들입니다.
```

## WebConfig → LoginCheckInterceptor

### **전체 흐름**

1. **설정 단계 (WebConfig)**
    - WebConfig 클래스에서 인터셉터를 등록하고 적용할 URL을 지정합니다.
2. **요청 처리 단계 (LoginCheckInterceptor)**
    - 사용자가 웹사이트에 요청을 보냅니다.
    - 요청된 URL이 인터셉터가 적용된 경로라면, LoginCheckInterceptor가 동작합니다.
3. **인터셉터 동작 (LoginCheckInterceptor의 메서드들)**
    - preHandle: 컨트롤러 실행 전 로그인 체크
    - postHandle: 컨트롤러 실행 후, 뷰 렌더링 전 추가 작업
    - afterCompletion: 모든 처리가 끝난 후 마무리 작업

### **상세 설명**

1. **WebConfig 설정**
    - LoginCheckInterceptor를 생성하고 등록합니다.
    - **`/member/myinfo.do`**와 **`/member/logout.do`**에 인터셉터를 적용합니다.
    - **`/index.do`**와 **`/member/login.do`**는 인터셉터 적용에서 제외합니다.
2. **사용자 요청 처리**
    - 사용자가 **`/member/myinfo.do`**에 접근하려고 합니다.
3. **LoginCheckInterceptor 동작**
    - preHandle 메서드가 실행됩니다.
    - 세션을 확인하여 로그인 상태를 체크합니다.
    - 로그인되지 않았다면 **`/index.do`**로 리다이렉트합니다.
    - 로그인되어 있다면 true를 반환하여 요청 처리를 계속합니다.
4. **컨트롤러 실행**
    - 인터셉터를 통과하면 해당 URL의 컨트롤러가 실행됩니다.
5. **postHandle 실행**
    - 컨트롤러 실행 후, 추가적인 작업이 필요하다면 수행합니다.
6. **뷰 렌더링**
    - 결과 페이지가 사용자에게 보여집니다.
7. **afterCompletion 실행**
    - 모든 처리가 끝난 후 최종 정리 작업을 수행합니다.


# Spring MVC Flow (정리)
1. Model (모델)
Model은 애플리케이션의 데이터와 비즈니스 로직을 나타냅니다. 이 구성 요소는 데이터를 표현하고, 컨트롤러에서 필요한 로직을 처리합니다.

주로 POJO (Plain Old Java Object) 형태로 구현됩니다.

데이터베이스와 상호작용하거나 비즈니스 로직을 처리하는 역할을 합니다.

2. View (뷰)
View는 사용자에게 보여지는 화면을 나타냅니다. 사용자가 데이터를 확인하거나 입력하는 인터페이스 역할을 합니다.

Spring MVC에서는 Thymeleaf 등 다양한 뷰 기술을 지원합니다.

뷰 리졸버(View Resolver)는 컨트롤러에서 반환된 뷰 이름을 실제 뷰 파일에 매핑하는 역할을 합니다.

3. Controller (컨트롤러)
Controller는 클라이언트의 요청을 받아 해당 요청을 처리하고, 그 결과를 Model에 담아 View를 반환합니다.

Spring에서 @Controller 어노테이션을 사용하여 컨트롤러를 정의합니다.

@RequestMapping (또는 @GetMapping, @PostMapping) 어노테이션을 사용하여 URL과 매핑된 메서드를 정의합니다.

4. DispatcherServlet (디스패처 서블릿)
DispatcherServlet은 Spring MVC의 핵심 구성 요소로, 클라이언트의 HTTP 요청을 받아서 적절한 컨트롤러로 전달하고, 그 결과를 다시 클라이언트에게 반환하는 역할을 합니다.

모든 요청을 중앙에서 처리하며, 다른 구성 요소와의 상호작용을 담당합니다.

5. HandlerMapping (핸들러 매핑)
HandlerMapping은 클라이언트의 요청 URL에 따라 어떤 컨트롤러를 실행할지를 결정하는 역할을 합니다.

Spring은 `RequestMappingHandlerMapping`을 사용하여 URL과 메서드의 어노테이션을 기준으로 컨트롤러를 연결합니다.

6. ViewResolver (뷰 리졸버)
ViewResolver는 컨트롤러에서 반환된 뷰 이름을 실제 뷰 파일에 매핑하는 역할을 합니다.

예를 들어, "userView"라는 뷰 이름을 받으면, `ViewResolver`는 이를 실제 JSP 파일이나 Thymeleaf 템플릿 파일로 매핑하여 렌더링합니다.

7. HandlerInterceptor (핸들러 인터셉터)
HandlerInterceptor는 요청 처리 과정에서 추가적인 작업을 하기 위해 사용됩니다. 주로 인증, 로깅, 성능 모니터링 등의 공통 관심사를 처리하는 데 유용합니다.

요청이 컨트롤러에 도달하기 전에 사전 작업을 하거나, 컨트롤러 처리 후 뷰가 렌더링되기 전에 후처리를 할 수 있습니다.

8. ExceptionResolver (예외 해결자)
ExceptionResolver는 Spring MVC에서 발생하는 예외를 처리하는 역할을 합니다. 예외가 발생했을 때, 이를 처리하고 적절한 뷰로 리턴하거나, 필요한 처리를 할 수 있습니다.

HandlerExceptionResolver 인터페이스를 구현하여 전역 예외 처리기를 정의할 수 있습니다.

Spring MVC 요청 흐름
클라이언트 요청: 사용자가 HTTP 요청을 보냄.

DispatcherServlet: 모든 요청을 `DispatcherServlet`이 받아들임.

HandlerMapping: 요청을 처리할 컨트롤러를 결정.

Controller: 요청을 처리하고, 필요한 데이터를 Model에 추가.

ViewResolver: 반환된 뷰 이름을 실제 뷰로 변환하여 사용자에게 반환.

View: 최종적으로 사용자에게 응답을 렌더링.
