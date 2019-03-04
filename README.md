
# The Trioz 프로젝트 구조

***

## 1. 패키지 분류

### 1.1 res

게임에 포함된 리소스가 들어있는 패키지이다. 리소스의 종류(그림, 음악 등)에 따라 하위 디렉토리로 분류되어 있다. 이 패키지에는 리소스 외에도 `XResource` 의 이름을 갖는 Enumerator들이 있다. 이는 *X* 타입의 리소스 파일과 프로그램을 연결해주며 해당 리소스와 관련된 프로세스를 내부적으로 처리해주는 역할을 한다.
예를 들어, `ImageResource`는 `res/images` 디렉토리 내의 각 이미지 파일과 일대일로 매칭된 객체들을 갖고 있으며, 각 파일로부터 `ImageIcon`을 가져오는 `getImageIcon()` 메서드를 갖고 있다.

### 1.2 com.dropfl

* `com.dropfl.activity` : `Activity`와 이를 상속한 클래스들이 있는 패키지.

* `com.dropfl.component` : 렌더링과 관련된 인터페이스가 있는 패키지.

* `com.dropfl.effect` : 화면 이펙트와 관련된 클래스가 있는 패키지.

* `com.dropfl.init` : 게임 초기화와 관련된 클래스가 있는 패키지.

* `com.dropfl.key` : 키 입력과 관련된 소스들이 있는 패키지.

* `com.dropfl.motion` : `Entity`의 움직임과 관련된 소스들이 있는 패키지.

* `com.dropfl.music` : 음악 재생과 관련된 클래스가 있는 패키지.

* `com.dropfl.platformer` : 게임의 플랫포머 엔진에 관련된 소스들이 있는 패키지.

  * `com.dropfl.platformer.collision` : 충돌판정과 관련된 소스들이 있는 패키지.
  * `com.dropfl.platformer.entity` : 엔티티(`Entity`)들이 정의된 패키지.
  * `com.dropfl.platformet.event` : 시간에 따라 발생하는 게임 내 이벤트가 정의된 패키지.

* `com.dropfl.util` : 코딩에서의 편의를 위한 클래스가 있는 패키지.

***

## 2. 핵심 객체

프로그램의 구조를 이해하는 데에 필요한 객체들은 다음과 같다.

### 2.1 Main

`main` 함수가 선언된 위치이자, 전역 설정을 담당하는 곳이다. 현재는 프로그램의 해상도가 (1280x720) 지정되어 있으며, 추후 `OptionActivity`에서의 설정 변경을 이 곳에 있는 변수를 변경하고 다른 `Activity`에서 이를 참조하는 방식으로 구현할 예정이다.

### 2.2 Activity

안드로이드의 액티비티 개념과 유사하다. 하나의 화면에 대해 일어나는 모든 작업들을 총괄하는 객체이다. `GameFrame`에서는 `activity` 객체를 치환하는 방식으로 화면의 변화를 구현할 예정이다. 다만 안드로이드에서 `Intent`와 같은 객체의 필요성을 인지하고 있으나, 여기서 어떻게 구현할지는 아직 결정되지 않았다.

### 2.3 IDrawable, ImageComponent

화면에 렌더링할 수 있는 객체들은 모두 `IDrawable` 인터페이스를 구현해야한다. 메서드는 단 하나만 정의되어있다.

```JAVA
void render (java.awt.Graphics2D);
```

이 프로젝트에서 렌더링을 컴포지트 패턴으로 구현했고, `IDrawable`이 해당 컴포넌트이다.

`ImageComponent`는 자체적인 이미지를 갖고있는 컴포넌트이며, `IDrawable`을 구현하였다. 좌표(`x`, `y`)와 회전 각도(`rotation`)에 따라 갖고 있는 이미지(`image`)를 그리는 `render` 메서드가 구현되어 있다.

### 2.4 BoundingBox, CollisionType

이 게임엔진에서 충돌판정 알고리즘을 결정할 때 스트래티지 패턴을 사용하며, 이에 쓰이는 객체가 `CollisionType`이다.

`BoundingBox`는 플랫포머 엔진 내 도형의 Bounding Box에 대한 데이터를 가져올 수 있는 메서드가 정의된 인터페이스이고, `CollisionType`는 이 메서드를 이용해 두 `BoundingBox`의 충돌을 판별하는 추상 클래스이다. 이 게임에 쓰이는 엔티티들은 모두 사각형 또는 원이기에 Bounding Box를 저장하는 것으로 각 도형을 충분히 표현할 수 있다. 이들을 이용해 `CollisionType`에서 적절한 알고리즘으로 충돌을 판정하는 메서드는 다음과 같다.

```JAVA
boolean isCollided (com.dropfl.platformer.collision.BoundingBox, com.dropfl.platformer.collision.BoundingBox);
```

현재 고려하고 있는 충돌판정 알고리즘은 원-사각형 충돌 알고리즘, AABB, OBB로 크게 3가지가 있으며, 각각이 구현된 `CIRC_2_SQ`, `AABB`, `OBB`가 정의되어있다.

### 2.5 Entity, PlayerInteractive

`Entity`는 말 그대로 엔티티로, `ImageComponent`를 상속하며 `BoundingBox`를 구현한 추상 클래스이다. `BoundingBox`에 정의된 메서드들의 구현이 담겨있으며, `Player`가 이를 상속한다. `PlayerInteractive`는 `Player`와 상호작용할 수 있는 엔티티로, `Entity`를 상속하며 다음의 두 메서드가 추가로 정의되어 있다.

```JAVA
boolean isCollided (com.dropfl.platformer.entity.Player);
boolean interact (com.dropfl.platformer.entity.Player);
```

각각 플레이어와 접촉했는지 확인하는 메서드, 플레이어와 상호작용하는 메서드이다. `isCollided` 함수는 온전히 `CollisionType`에게 위임되어 있지만. `interact` 함수는 구현되어있지 않다. `interact` 메서드의 리턴값은 상호작용 후 해당 엔티티의 삭제가 필요한지를 `Engine`에게 알려주는 역할을 한다. (`true`면 삭제이다.) 이 때 제거용 `PlayerInteractive.destroy()` 메서드의 필요성을 검토하고 있지만 아직 확정되진 않았다.

#### 2.5.1 ImprovedEntity, EntityFactory

*아직 완성되지 않은 클래스로, 다음은 구현 목표를 설명한 것이다.*

현재 `Entity`는 이 프로젝트에서 정의된 `Bullet`, `Fireball`, `Player` 등으로 국한되는데, `ImprovedEntity`는 이러한 한계를 극복하기 위해 고안되었다. 해당 엔티티는 `EntityFactory`로부터 생성될 수 있는데, 이 팩토리는 파일로부터 텍스쳐와 후술할 `Motion` 등 각종 리소스를 수집하여 `ImprovedEntity`의 인스턴스에 연결시킨다. 파일의 포맷은 JSON의 형태로, [entity.ts](https://github.com/DropFL/trioz/blob/master/doc/entity.ts)에 정의되어 있다.

### 2.6 Key, KeyStatus

`KeyStatus`는 키 입력을 주관하는, 인스턴스화할 수 없는 클래스이다. `KeyStatus.init()`으로 초기화를 진행하고 `KeyStatus.register(java.awt.Component)`로 해당 `Component`에 들어오는 입력을 받을 수 있다. 키 입력을 확인하는 메서드는 다음과 같다.

```JAVA
boolean isKeyPressed (com.dropfl.key.Key);
boolean isKeyJustPressed (com.dropfl.key.Key);
```

전자는 키가 단순히 눌려있는지를 조사하며, 후자는 키 입력이 아직 처리되지 않았는지를 추가로 조사한다. `KeyStatus`에게 키 입력이 처리되었음을 알려주는 메서드는 다음과 같다.

```JAVA
void setKeyProcessed (com.dropfl.key.Key);
```

멀티쓰레딩 환경에서는 다소 위험성이 있는 방식이지만, 이 프로젝트에서는 최대 1개의 객체가 키 입력에 반응하기 때문에 큰 무리가 없다고 판단하였다.

* `KeyListener`를 통한 구현에서 문제가 발생하여 `KeyBinding`으로 구현 방식을 바꾸었다. 사용 방법은 이전과 동일하다.

### 2.7 TickEvent

**_구조를 크게 개편할 예정에 있음을 유의하여 읽어주길 바란다._**

`TickEvent`는 게임 내 시간 단위인 `tick`에 따라 발생하는 게임 내 이벤트를 대표하는 클래스이다. `TickEvent`는 언제부터 (`since`) 얼마동안 (`duration`) 어떻게 (`formula`) 제어할 것인지 정의되어야 한다.

`formula`는 `Integer -> Double[]`의 함수 (`Function<Integer, Double[]>`) 로 정의된다. 인자로 들어가는 `Integer`는 `since`로부터 흘러간 시간이며, `Double[]`은 해당 이벤트가 제어하는 대상에 대한 속성이 정의되어야 한다. 가령, `SpeedEvent`는 `Engine` 내에 있는 `speed` 변수로 설정할 값을 리턴한다.

`formula`가 `Function` 인터페이스이기에 선언할 때 다소 번거롭지만, 매우 높은 자유도를 통해 더욱 다양한 형태로 제어할 수 있다. 추가적으로 `Formula`라는 추상클래스를 만들어 인터페이스가 갖는 한계를 해소할 수 있다.

### 2.8 EventManager

`EventManager`는 게임에 사용될 모든 `TickEvent`의 목록이 정의되고 그것을 제어하는 클래스이다. 현재는 `TickEvent`가 이 안에 하드코딩되어 있으나, 파일로 입력을 받게 되면 초기화할 때의 과부하 문제와 경직성을 해결할 수 있다.

### 2.9 Motion

*아직 완성되지 않은 클래스로, 다음은 구현 목표를 설명한 것이다.*

`Entity`들은 `Player` 및 자신의 좌표, 시간의 흐름 등에 따라 다양한 움직임을 보일 수 있어야 한다. 그러한 인자들이 선언된 `MotionFactor`로부터 생성된 `MotionArgument`을 이용하여 어떤 속성의 현재 값을 계산해주는 클래스가 `Motion`이다.

실제 구현은 다음과 같이 크게 두 가지의 옵션을 생각하고 있다. [mXparser](http://mathparser.org/) 또는 [LuaJ](http://www.luaj.org/luaj/3.0/README.html)를 이용해 식 또는 코드를 바탕으로 계산하는 방식으로 계획 중이다.

* [mXparser](http://mathparser.org/)를 이용해 직접 `String`으로 적힌 식을 계산하는 방식. 이 경우 `Motion`을 정의하는 리소스 자체의 크기가 작고 계산의 정확도 등에서 이점을 갖는다.

* [LuaJ](http://www.luaj.org/luaj/3.0/README.html)를 이용해 Lua로 코딩된 작은 프로그램을 실행시키는 방식. 이 경우 위의 mXparser에 비해 라이브러리의 크기가 작으며 컴파일된 바이너리로 성능의 향상을 생각할 수도 있고, 더 높은 자유도가 있다는 장점이 있다. 향후 기능 확장을 고려했을 때 압도적으로 유리하다.

***

## 3. 프로그램 구조

`Main`에서 `KeyStatus`를 초기화하고 `GameFrame` 인스턴스를 생성하여 이를 `KeyStatus`에 등록한다. `GameFrame`은 `JFrame`을 상속하는 클래스로 실제로는 여기의 `paint` 메서드를 통해 렌더링이 일어난다. 이 때 사용하는 Double Buffering 기법에서 첫번째 렌더링을 `Activity` 에게 온전히 위임하고 두번째 렌더링으로써 실제 화면에 표시하는 작업을 수행한다.

그 중에서도 `PlatformerActivity`는 렌더링을 3단계로 나누어 수행하는데, 이는 다음과 같다.

1. 게임 플레이 화면보다 **뒤에** 와야하는 요소들의 렌더링 (배경화면 등)
2. 실제 게임 플레이 화면 렌더링 (`Engine`에게 위임)
3. 게임 플레이 화면보다 **앞에** 와야하는 요소들의 렌더링 (게임 내 이펙트 포함, 현재는 해당 요소가 없음)

`Engine`에서도 `Player` 객체와 `PlayerInteractive` 객체들을 대상으로 `render`를 호출하는 방식으로 렌더링을 수행한다. `Engine`은 자체적인 `tick()` 함수를 통해 한 프레임씩 게임을 진행시킨다. `MusicPlayer`의 진행과 `Engine`, `EventManager` 등의 동기화는 `Synchronizer`를 통해 이루어진다.

### 3.1 TODO

1. `ImageComponent`의 `x`, `y` 속성을 `Point`로 대체한다.
2. `Motion`의 기능을 완성한다.
3. `ImprovedEntity`와 `EntityFactory`를 코딩한다.
4. 하드코딩된 `TickEvent`들을 파일로 옮긴다.
5. 렌더링의 순서를 z-index와 같이 숫자로 수정할 필요가 있다.
6. `OptionActivity`, `SongSelectActivity` 등을 구현한다.
