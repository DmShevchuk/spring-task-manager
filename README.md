# Shevchuk_DO_Java_2022_Liga

### Чтобы запустить приложение

```shell
git clone https://github.com/DmShevchuk/Shevchuk_DO_Java_2022_Liga
cd Shevchuk_DO_Java_2022_Liga
./gradlew build -x test
cd docker
docker-compose down -v
docker-compose up --build
```
#### ИЛИ

```shell
git clone https://github.com/DmShevchuk/Shevchuk_DO_Java_2022_Liga
cd Shevchuk_DO_Java_2022_Liga
run_build.sh
```