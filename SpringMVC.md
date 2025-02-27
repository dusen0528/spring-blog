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
