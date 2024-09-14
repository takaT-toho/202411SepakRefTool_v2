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
#### VPSのセットアップ
- aptの最新化
`sudo apt update && sudo apt upgrade -y`
- Javaが入っているか確認
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
