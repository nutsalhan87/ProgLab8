package client.locales;

import java.util.ListResourceBundle;

public class Locale_ru extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"login", "Вход"},
                {"register", "Регистрация"},
                {"welcome", "Добро пожаловать в путеводитель по Дагестану!"},
                {"russian", "Русский"},
                {"norwegian", "Норвежский"},
                {"italian", "Итальянский"},
                {"spanish", "Испанский"},
                {"language", "Язык"},
                {"username", "Имя пользователя"},
                {"password", "Пароль"},
                {"user", "Пользователь"},
                {"filters", "Фильтры"},
                {"script", "Выполнение скрипта"},
                {"map", "Карта"},
                {"dagestanBase", "База пользовательских маршрутов по Дагестану"},
                {"removeElements", "Удалить все мои элементы"},
                {"exit", "Выйти"},
                {"startsWith", "Начинается с подстроки"},
                {"contains", "Содержит подстроку"},
                {"scriptPath", "Путь до скрипта"},
                {"execute", "Выполнить"},
                {"name", "Имя"},
                {"fromName", "From имя"},
                {"distance", "Дистанция"},
                {"remove", "Удалить"},
                {"send", "Отправить"},
                {"ifMax", "Изменить или добавить, если элемент максимальный"},
                {"distanceRemove", "Удалить только по дистанции"},
                {"lowerRemove", "Удалить все меньшие, чем этот"},
                {"greaterRemove", "Удалить все большие, чем этот"},
                {"creationDate", "Дата создания"},
                {"owner", "Создатель маршрута"},
                {"noScriptInScript", "Нельзя вызвать 'execute_script' внутри скрипта"},
                {"noFileScript", "Файл скрипта недоступен или не найден"},
                {"wrongCommand", "Ошибка: либо такой команды нет, либо недостаточно аргументов"},
                {"emptyInput", "Введена пустая строка"},
                {"onlyRoute", "Программа поддерживает только работу с 'Route'"},
                {"unavailableId", "Объект с таким id недоступен для обновления"},
                {"onlyPositiveInt", "В качестве id должно быть введено целое положительное число"},
                {"noExitInScript", "Команда 'exit' запрещена для выполнения в скрипте"},
                {"correctDistance", "Введите корректную дистанцию в виде вещественного числа"},
                {"help", "help: вывести справку по доступным командам\n" +
                        "info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, " +
                        "количество элементов и т.д.)\n" +
                        "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                        "add {element}: добавить новый элемент в коллекцию\n" +
                        "update id: обновить значение элемента коллекции, id которого равен заданному\n" +
                        "remove_by_id id: удалить элемент из коллекции по его id\n" +
                        "clear: очистить коллекцию\n" +
                        "execute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся" +
                        " команды в таком же виде, в котором их вводит пользователь в интерактивном режиме\n" +
                        "add_if_max {element}: добавить новый элемент в коллекцию, если его значение превышает значение" +
                        " наибольшего элемента этой коллекции\n" +
                        "remove_greater {element}: удалить из коллекции все элементы, превышающие заданный\n" +
                        "remove_lower {element}: удалить из коллекции все элементы, меньшие, чем заданный\n" +
                        "remove_any_by_distance distance: удалить из коллекции один элемент, значение поля distance" +
                        " которого эквивалентно заданному\n" +
                        "filter_contains_name name: вывести элементы, значение поля name которых содержит заданную подстроку\n" +
                        "filter_starts_with_name name: вывести элементы, значение поля name которых начинается с" +
                        " заданной подстроки"},
                {"addSuccessful", "Новый экземпляр класса успешно добавлен в коллекцию"},
                {"addUnsuccessful", "Экземпляр класса не удалось добавить в базу данных"},
                {"updateIdSuccessful", "Объект с таким id успешно обновлен"},
                {"updateIdUnsuccessful", "Обновления не произошло"},
                {"removeIdSuccessful", "Объект с таким id, если таковый был, успешно удален"},
                {"removeUnsuccessful", "Удаления не произошло"},
                {"yourDataRemoved", "Ваши данные, если таковые были, удалены из коллекции"},
                {"isNotMax", "Новый объект не больше максимального элемента коллекции, потому не был добавлен"},
                {"removedGreater", "Элементы коллекции, превышающие заданный, успешно удалены"},
                {"removedLower", "Элементы коллекции, которые меньше заданного, успешно удалены"},
                {"removeDistanceSuccessful", "Первый встречный элемент в коллекции, " +
                        "значение distance которого равно заданному, если таковой был найден, удален"},
                {"elementNotFound", "Такой элемент не найден"},
                {"valuesRestrictions", "Проверьте данные ограничения:\n" +
                        "Ни одно поле не может быть пустым\n" +
                        "Все поля, кроме имени и имени From, должны быть числами\n" +
                        "X должен быть больше -140\n" +
                        "Дистанция должна быть больше 1"},
                {"port", "Порт"},
                {"inputPositiveIntPort", "В качестве порта введите целое положительное число"},
                {"invalidPort", "Не удалось подключиться по данному порту"},
                {"authorizationUnsuccessful", "Не удалось авторизоваться"},
                {"registerUnsuccessful", "Не удалось создать аккаунт"}
        };
    }
}