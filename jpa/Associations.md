# Association
Associations describe how two or more entities form a relationship based on a database joining semantics.

연관은 두 개 이상의 엔터티가 데이터베이스 조인 의미론을 기반으로 관계를 형성하는 방법을 설명합니다.

## 1. ``@ManyToOne``
``@ManyToOne`` is the most common association, having a direct equivalent in the relational database as well (e.g. foreign key), and so it establishes a relationship between a child entity and a parent.

``@ManyToOne``관계형 데이터베이스에서도 직접적으로 동등한 항목(예: 외래 키)을 갖는 가장 일반적인 연관이므로 하위 엔터티와 상위 엔터티 간의 관계를 설정합니다.

Each entity has a lifecycle of its own. Once the ``@ManyToOne`` association is set, Hibernate will set the associated database foreign key column.

각 엔터티에는 고유한 수명 주기가 있습니다. ``@ManyToOne`` 연관이 설정 되면 Hibernate는 연관된 데이터베이스 외래 키 열을 설정할 것입니다

<details>
    <summary>예시</summary>

 ```java
    @Entity(name = "Person")
    public static class Person {

	@Id
	@GeneratedValue
	private Long id;

	//Getters and setters are omitted for brevity

    }

    @Entity(name = "Phone")
    public static class Phone {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "`number`")
	private String number;

    @ManyToOne
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "PERSON_ID_FK"))
    private Person person;
   ```
```sql
    CREATE TABLE Person (
        id BIGINT NOT NULL ,
        PRIMARY KEY( id )
    )

    CREATE TABLE Phone (
        id BIGINT NOT NULL ,
        number VARCHAR(255) ,
        person_id BIGINT ,
        PRIMARY KEY ( id )
    )

    ALTER TABLE Phone
    ADD CONSTRAINT PERSON_ID_FK
    FOREIGN KEY (person_id) PREFERENCES Person
```
 ```java
Person person = new Person();
entityManager.persist(person);

Phone phone = new Phone("123-456-7890");
phone.setPerson(person);
entityManager.persist(phone);

entityManager.flush();
phone.setPerson(null);
```
```sql
INSERT INTO Person ( id )
VALUES ( 1 )

INSERT INTO Phone ( number, person_id, id )
VALUES ( '123-456-7890', 1, 2 )

UPDATE Phone
SET number = '123-456-7890'.
    person_id = NULL
WHERE id = 2
```

</details>

## 2. ``@OneToMany``
The ``@OneToMany`` association links a parent entity with one or more child entities. If the ``@OneToMany`` doesn’t have a mirroring ``@ManyToOne`` association on the child side, the ``@OneToMany`` association is unidirectional. If there is a ``@ManyToOne ``association on the child side, the ``@OneToMany`` association is bidirectional and the application developer can navigate this relationship from both ends.

연결 은 ``@OneToMany``상위 엔터티를 하나 이상의 하위 엔터티와 연결합니다. 하위 측에 ``@ManyToOne`` 연관과 미러링 없으면 단방향입니다. 자식 측에 ``@ManyToOne`` 연관이 있는 경우 양방향이며 애플리케이션 개발자는 양쪽 끝에서 이 관계를 탐색할 수 있습니다.

When using a unidirectional ``@OneToMany`` association, Hibernate resorts to using a link table between the two joining entities.

단방향 ``@OneToMany`` 연관을 사용할 때 Hibernate는 결합된 두개의 엔티티 사이의 링크 테이블을 사용한다.

The ``@OneToMany`` association is by definition a parent association, regardless of whether it’s a unidirectional or a bidirectional one. Only the parent side of an association makes sense to cascade its entity state transitions to children.

``@OneToMany`` 연관은 단방향인지 양방향인지 상관없이 부모 연결입니다. 연결의 부모 측에서만 엔티티 상태 전환을 자식에게 계단식으로 전달하는 것이 합리적입니다.

<details>
    <summary>예시</summary>

```java
@Entity(name = "Person")
public static class Person {

	@Id
	@GeneratedValue
	private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();
}

@Entity(name = "Phone")
public static class Phone {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "`number`")
	private String number;
}
```
```sql
CREATE TABLE Person (
    id BIGINT NOT NULL ,
    PRIMARY KEY ( id )
)

CREATE TABLE Person_Phone (
    Person_id BIGINT NOT NULL ,
    phones_id BIGINT NOT NULL
)

CREATE TABLE Phone (
    id BIGINT NOT NULL ,
    number VARCHAR(255) ,
    PRIMARY KEY (id)
)

ALTER TABLE Person_Phone
ADD CONSTRAINT UK_9uhc5itwc9h5gcng944pcaslf
UNIQUE (phones_id)

ALTER TABLE Person_Phone
ADD CONSTRAINT  FKr38us2n8g5p9rj0b494sd3391
FOREIGN KEY (phones_id) REFERENCES Phone

ALTER TABLE Person_Phone
ADD CONSTRAINT FK2ex4e4p7w1cj310kg2woisjl2
FOREIGN KEY (Person_id) References Person
```
```java
Person person = new Person();
Phone phone1 = new Phone("123-456-7890");
Phone phone2 = new Phone("321-654-0987");

person.getPhones().add(phone1);
person.getPhones().add(phone2);
entityManage.persist(person);
entityManager.flush();

person.getPhones().remove(phone1);
```
```sql
INSERT INTO Person
        ( id )
VALUES  ( 1 )

INSERT INTO Phone
        ( number, id )
VALUES  ('123-456-7890', 2)

INSERT INTO Person_Phone
        ( Person_id, phones_id )
VALUES ( 1, 2 )

INSERT INTO Person_Phone
        ( Person_id, phones_id )
VALUES ( 1, 3 )

DELETE FROM Person_Phone
WHERE Person_id = 1

INSERT INTO Person_Phone
        ( Person_id, phones_id )
VALUES ( 1, 3 )

DELETE FROM Phone
WHERE id = 2
```

</details>

## 3. ``@OneToOne``
The ``@OneToOne`` association can either be unidirectional or bidirectional. A unidirectional association follows the relational database foreign key semantics, the client-side owning the relationship. A bidirectional association features a ``mappedBy`` ``@OneToOne`` parent side too.

``@OneToOne`` 연관은 단방향이거나 양방향일 수 있다. 단방향이면 관계를 소유하는 클라이언트 측인 관계형 데이터베이스 외래 키 의미 체계를 따른다. 양방향이면 ``mappedBy`` ``@OneToOne`` 측도 포함된다.

From a relational database point of view, the underlying schema is identical to the unidirectional ``@ManyToOne`` association, as the client-side controls the relationship based on the foreign key column.

관계형 데이터베이스 관점에서 클라이언트 측에서 외래 키를 기반으로 관계를 제어하므로 기본 스키마는 단방향 ``@ManyToOne``연관과 동일하다.

But then, it’s unusual to consider the Phone as a client-side and the PhoneDetails as the parent-side because the details cannot exist without an actual phone. A much more natural mapping would be the Phone were the parent-side, therefore pushing the foreign key into the PhoneDetails table. This mapping requires a bidirectional ``@OneToOne`` association as you can see in the following example:

하지만, 실제 ``Phone`` 없이는 세부 사항이 존재할 수 없기 때문에 클라이언트측과 ``PhoneDetails``를 부모측으로 간주하는 것은 이례적이다. 훨씬 더 자연스러운 매핑은 ``Phone``이 부모측이어야 하고, 그러므로 외래키를 ``PhoneDetails`` 테이블에 넣는다. 이 매핑은 다음 예시에서 볼 수 있듯이 양방향 ``@OneToOne``연결이 필요하다.

<details>
    <summary>예시</summary>

```java
@Entity(name = "Phone")
public static class Phone {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "`number`")
	private String number;

    @OneToOne
    @JoinColumn(name = "deatils_id")
    private PhoneDetails details;
}

@Entity(name = "PhoneDetails")
public static class PhoneDetails {

	@Id
	@GeneratedValue
	private Long id;

    private String provider;
    private String technology;
}
```
```sql
CREATE TABLE Phone (
    id BIGINT NOT NULL ,
    number VARCHAR(255) ,
    details_id BIGINT ,
    PRIMARY KEY (id)
)

ALTER TABLE Phone
ADD CONSTRAINT FKnoj7cj83ppfqbnvqqa5kolub7
FOREIGN KEY (details_id) PREFERENCES PhoneDetails
```

</details>

### Reference
* https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#associations
* https://dev-coco.tistory.com/106