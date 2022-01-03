# Calculator
Решение модуля калькулятор.

Поддерживаемые мат операции: ( ) + - * / ^
Поддерживаются отрицательные числа заключенные в скобочки
Для расширения функциональности, необходимо в классе MathOperation дополнить поле operationPriority и добавить логику в метод doMathOperation.

Выражение: (-2) - ((-4) * 3.5) является корректным, вывод равен 12.
            4.2 + 2 * 3 / 3 - 6.1 = 0.1
            3 + 4 * 2 / ( 1 - 5 ) ^ 2 = 3.5
            1 + 2 * ( 3 + 4 / 2 - ( 1 + 2 ) ) * 2 + 1 = 10
Обработка исключений:            
            1 + 2 * ( 3 + 4 / 2 - ( 1 + 2 ) ) * 2 ++ 1 выводит ошибку :com.epam.izh.rd.online.exception.MathematicSymbolException: Неподдерживаемая операция ++
            abv = com.epam.izh.rd.online.exception.WrongInputStringExpression: Некорректно введено выражение (предоставляется возможность повторного ввода выражения)
            (-2) - ((-4) * 3.5) +     com.epam.izh.rd.online.exception.WrongInputStringExpression: Некорректно введено выражение
            Обработка ввода пустой строки
            2 / 0     com.epam.izh.rd.online.exception.DivisionByZero: Деление на ноль запрещено
            
