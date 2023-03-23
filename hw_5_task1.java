// Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.
//Добавить функции: 1) Добавление номера и 2) Вывод всего

import java.util.*;

public class hw_5_task1 {
    public static void main( String[] args ) {
        Map<String, List<String>> phoneBook = new HashMap<>();
        Scanner iScanner = new Scanner( System.in, "UTF-8" );
        String name;

        boolean exit = false;
        while ( !exit ) {
            System.out.println( "\nДобро пожаловать в телефонный справочник!" );
            System.out.println( "\nВведите одну из следующих команд: " );
            System.out.println( "1. Добавить контакт в справочник" );
            System.out.println( "2. Отобразить список доступных контактов" );
            System.out.println( "3. Изменить телефонный номер" );
            System.out.println( "4. Удалить контакт из справочника" );
            System.out.println( "5. Поиск телефона по имени" );
            System.out.println( "6. Поиск имени по номеру телефона" );
            System.out.println( "\n0. Завершить работу справочника" );
            System.out.println( "\nОжидаю ввода команды: " );
            String input = iScanner.nextLine();
            switch ( input ) {
                case "1":
                    System.out.print( "\nВведите имя контакта: " );
                    name = iScanner.nextLine();
                    System.out.print( "Введите номер телефона: " );
                    String phoneNumber = getPhoneNumberFromUser( iScanner );
                    addContact( phoneBook, name, phoneNumber );
                    break;
                case "2":
                    printAllContacts( phoneBook );
                    break;
                case "3":
                    System.out.print( "\nВведите имя контакта: " );
                    name = iScanner.nextLine();
                    System.out.print( "Введите старый номер телефона: " );
                    String oldPhoneNumber = getPhoneNumberFromUser( iScanner );
                    System.out.print( "Введите новый номер телефона: " );
                    String newPhoneNumber = getPhoneNumberFromUser( iScanner );
                    updatePhoneNumber( phoneBook, name, oldPhoneNumber, newPhoneNumber );
                    break;
                case "4":
                    System.out.print( "\nВведите имя контакта: " );
                    name = iScanner.nextLine();
                    removeContact( phoneBook, name );
                    break;
                case "5":
                    System.out.print( "\nВведите имя контакта: " );
                    name = iScanner.nextLine();
                    findPhoneNumbersByContact ( phoneBook, name );
                    break;
                case "6":
                    System.out.print( "\nВведите номер телефона: " );
                    String phoneToFind = getPhoneNumberFromUser( iScanner );
                    findContactByPhoneNumber( phoneBook, phoneToFind );
                    break;
                case "0":
                    System.out.print( "\nДля подтверждения введите 'Y': " );
                    String answer = iScanner.nextLine().toLowerCase();
                    if ( answer.equals( "y" ) ) {
                        exit = true;
                    }
                    break;
                default:
                    System.out.println( "\nНеверный ввод\n" );
            }
        }
        iScanner.close();
    }
    // user pn input
    public static String getPhoneNumberFromUser( Scanner iScanner ) {
        String phoneNumber = iScanner.nextLine();
        while ( !isNumeric( phoneNumber ) ) {
            System.out.println( "Введены некорректные данные! Номер телефона должен состоять только из цифр: " );
            phoneNumber = iScanner.nextLine();
        }
        return phoneNumber;
    }
    // input pn check
    public static boolean isNumeric( String str ) {
        if ( str == null || str.length() == 0 ) {
            return false;
        }
        for ( char c : str.toCharArray() ) {
            if ( !Character.isDigit( c ) ) {
                return false;
            }
        }
        return true;
    }
    // 1
    public static void addContact( Map<String, List<String>> phoneBook, String name, String phoneNumber ) {
        if ( !phoneBook.containsKey( name ) ) {
            phoneBook.put( name, new ArrayList<>());
        }
        phoneBook.get( name ).add( phoneNumber );
        System.out.println("\nНомер телефона " + phoneNumber + " добавлен контакту " + name + "" );
    }
    // 2
    public static void printAllContacts( Map<String, List<String>> phoneBook ) {
        if ( phoneBook.isEmpty() ) {
            System.out.println( "\nТелефонный справочник пуст! :( " );
        } else {
            System.out.println( "\nСписок контактов:" );
            for ( Map.Entry<String, List<String>> entry : phoneBook.entrySet() ) {
                System.out.println( entry.getKey() + ": " + entry.getValue() );
            }
        }
    }
    // 3
    public static void updatePhoneNumber( Map<String, List<String>> phoneBook, String name, String oldPhoneNumber, String newPhoneNumber ) {
        List<String> phoneNumbers = phoneBook.get( name );
        if ( phoneNumbers == null ) {
            System.out.println( "Контакт " + name + " не найден в телефонной книге" );
            return;
        }
        int index = phoneNumbers.indexOf( oldPhoneNumber );
        if ( index == -1 ) {
            System.out.println("Старый номер телефона " + oldPhoneNumber + " для контакта " + name + " не найден в телефонной книге" );
            return;
        }
        phoneNumbers.set( index, newPhoneNumber );
        System.out.println( "Старый номер телефона " + oldPhoneNumber + " для контакта " + name + " успешно изменен на " + newPhoneNumber );
    }
    // 4
    public static void removeContact( Map<String, List<String>> phoneBook, String name ) {
        List<String> phoneNumbers = phoneBook.remove( name );
        if ( phoneNumbers != null ) {
            System.out.println( "Контакт " + name + " успешно удален из телефонной книги" );
        } else {
            System.out.println( "Контакт " + name + " не найден в телефонной книге" );
        }
    }
    // 5
    public static void findPhoneNumbersByContact( Map<String, List<String>> phoneBook, String nameToFind ) {
        List<String> phoneNumbersFound = phoneBook.get( nameToFind );
        if ( phoneNumbersFound != null ) {
            System.out.println( "Номера телефона " + nameToFind + ": " );
            for ( String phoneNumberFound : phoneNumbersFound ) {
                System.out.println( phoneNumberFound );
            }
        } else {
            System.out.println( "Контакт "+ nameToFind +" не найден в телефонной книге" );
        }
    }
    // 6
    public static void findContactByPhoneNumber( Map<String, List<String>> phoneBook, String phoneNumber ) {
        boolean contactFound = false;
        for ( Map.Entry<String, List<String>> entry : phoneBook.entrySet() ) {
            String name = entry.getKey();
            List<String> phoneNumbers = entry.getValue();
            if ( phoneNumbers.contains( phoneNumber ) ) {
                System.out.println( "Номер телефона " + phoneNumber + " принадлежит контакту " + name );
                contactFound = true;
            }
        }
        if ( !contactFound ) {
            System.out.println( "Контакт с номером телефона " + phoneNumber + " не найден в телефонной книге" );
        }
    }
}


