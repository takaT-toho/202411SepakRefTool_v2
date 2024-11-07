# 2024年11月 セパ大会 審判ツール

#### ローカルからGitHubへ
- GitHubに新しいリポジトリを作成する。
- ローカルのプロジェクトをこのリポジトリにプッシュする。
  - .gitignoreファイルの作成
  - プロジェクトのフォルダ内においてgitコマンドを叩く
  ```git init
   git remote add origin URL
   git add .
   git branch -m master main
   git commit -m "Initial commit"
   git config --global user.name NAME
   git config --global user.password PASSWORD
   git push origin main
  ```
#### AWSのEC2(Ubuntu)のセットアップ
- 必要なソフトウェアのインストール
  ```
  java -version
  sudo apt update && sudo apt upgrade -y
  sudo apt install -y openjdk-21-jdk
  java -version
  sudo apt install -y maven git tomcat10
  sudo apt install mysql-server
  sudo systemctl start mysql.service
  ```
- MySQLの初期設定
  ```
  sudo mysql
  ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
  exit
  mysql -u root -p
  ALTER USER 'root'@'localhost' IDENTIFIED WITH auth_socket;
  sudo mysql_secure_installation
  sudo mysql
  CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';
  GRANT CREATE, ALTER, DROP, INSERT, UPDATE, INDEX, DELETE, SELECT, REFERENCES, RELOAD on *.* TO 'username'@'localhost' WITH GRANT OPTION;
  FLUSH PRIVILEGES;
  exit
  mysql -u username -p
  show variables like '%char%';
  systemctl status mysql.service
  ```
- GitHubからプロジェクトのクローン
  ```
  git clone https://github.com/takaT-toho/202411SepakRefTool_v2.git
  cd your-project
  ```
- MySQLの初期データベース作成
  ```
  mysql -u username -p
  source ~/202411SepakRefTool/initDatabase.sql
  ```
- GitHubのファイル追加および編集
  ```
  vi DB.properties
  ```
  ```
  URL=jdbc:mysql://localhost:3306/sendai?allowPublicKeyRetrieval=true&useSSL=false
  USER=XXXXX
  PASSWORD=XXXXX
  ```
  ```
  vi pom.xml
  ```
  ```
  <maven.compiler.source>21</maven.compiler.source>
  <maven.compiler.target>21</maven.compiler.target>

  <plugin>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.0</version>
    <configuration>
      <release>21</release>
    </configuration>
  </plugin>
  ```
  ```
  cd src/main/java/dao
  vi ConnectionManager.java
  ```
  ```
  private static final String FILENAME = "/home/ubuntu/202411SepakRefTool/DB.properties";
  ```
- Mavenを使用してプロジェクトをビルド
  ```
  cd ~/202411SepakRefTool_v2
  mvn clean package
  ```
- 生成されたWARファイルをTomcatのwebappsディレクトリにコピー
  ```
  sudo cp target/202411SepakRefTool_v2.war /var/lib/tomcat10/webapps/
  ```
- Tomcatを再起動
  ```
  sudo systemctl restart tomcat10
  ```
- ファイアウォールの設定
  ```
  sudo ufw allow 8080/tcp
  sudo ufw enable
  ```
- Webアプリにアクセス
  ```
  http://your-ec2-public-ip:8080/demo202411/judgeFC
  ```
- エラーログを見る
  ```
  /var/lib/tomcat10/catalina.out
  ```

#### VPS(ubuntu)のセットアップ
`java -version`
- OpenJDK 21のインストール
`apt install openjdk-21-jre-headless`
- Javaが正常に動くか確認
`java -version`
- 環境変数の設定
 - `vi ~/.bashrc`
  ```
  export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
  export PATH=$PATH:$JAVA_HOME/bin
  ```
 - bashrcファイルを取得して変更を適用
`source ~/.bashrc`
 - 設定の確認
  ```
  echo $JAVA_HOME
  echo $PATH
  ```
  ```
  /usr/lib/jvm/java-21-openjdk-amd64
  ```
- Tomcat 10のインストール
  - システムユーザーを作成する
    - `sudo useradd -r -m -U -d /opt/tomcat -s /bin/false tomcat`
  - インストールする
    - `wget -c https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.29/bin/apache-tomcat-10.1.29.tar.gz`
  - 設定ファイルを作成
  `vi /etc/systemd/system/tomcat.service`
  ```
  [Unit]
  Description=Apache Tomcat Web Application Container
  After=network.target
  
  [Service]
  Type=forking
  
  Environment="JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64"
  Environment="CATALINA_PID=/opt/tomcat/updated/temp/tomcat.pid"
  Environment="CATALINA_HOME=/opt/tomcat/updated/"
  Environment="CATALINA_BASE=/opt/tomcat/updated/"
  Environment="CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC"
  Environment="JAVA_OPTS=-Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom"
  
  ExecStart=/opt/tomcat/updated/bin/startup.sh
  ExecStop=/opt/tomcat/updated/bin/shutdown.sh
  
  User=tomcat
  Group=tomcat
  UMask=0007
  RestartSec=10
  Restart=always
  
  [Install]
  WantedBy=multi-user.target
  ```
