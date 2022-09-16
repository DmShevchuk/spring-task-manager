# spring-task-manager

Аккаунт администратора:
- *email* - admin@admin.ru
- *пароль* - adminAdmin

### Чтобы запустить приложение

```shell
git clone https://github.com/DmShevchuk/spring-task-manager
cd spring-task-manager
./gradlew build -x test
cd docker
docker-compose down -v
docker-compose up --build
```
#### ИЛИ

```shell
git clone https://github.com/DmShevchuk/spring-task-manager
cd spring-task-manager
run_build.sh
```
