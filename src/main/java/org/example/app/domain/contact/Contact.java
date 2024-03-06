package org.example.app.domain.contact;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
// https://projectlombok.org/features/Data
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {

    // @Id
    // Визначає первинний ключ об'єкта.
    //
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Для автоматичного генерування значення первинного ключа.
    // Визначає стратегію генерації значень первинних ключів.
    // GenerationType.IDENTITY вказує, що первинні ключі для сутності
    // повинні призначатися, використовуючи стовпець ідентифікації БД.
    // Вони автоматично збільшуються.
    //
    // @Column (name = "id")
    // Вказує зіставлення стовпців в БД.
    // Атрибут name використовується для вказівки імені стовпця таблиці.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Тут, найменування стовпця в БД
    // не збігається із найменуванням змінної.
    // Атрибут name вирішує проблему.
    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Override
    public String toString() {
        return "{" +
                "\"id\" : " + id +
                ", \"name\" : \"" + name + "\"" +
                ", \"phone\" : \"" + phone + "\"" +
                "}";
    }
}
