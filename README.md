#B1グランプリシステム

##仕様
このシステムは2018年度高専祭にて使用されます。
出店団体はこのアプリをインストールして使用することで、売り上げの履歴を見ることができます。
また、くじ引き機能もついており、これによりNIT2.0の企画と連携することができます。

このシステムは以下の機能を有す
####ログインページ
####メインページ
####売上履歴表示ページ
####QRコード読み取りページ
####くじ引き機能
####口コミ表示ページ


###ログイン機能
各団体はまず、配布されたトークンを入力し、新規登録を行います。
新規登録時のID、パスワードはデータベースに保存されます。
以降はこのID、パスワードでログインすることが可能です。
2日間の高専祭期間で、違う端末を用いることができます。

###メイン画面
B1グランプリのイメージデザインが表示されます。
ここでは、
設定・売上履歴ボタン・グラフ表示ボタン・QRコード読み取りボタン・口コミ表示ボタン
が表示されます。このボタンを押すことで各機能を使用することができます。

###売上履歴表示ページ
これまでの売り上げの詳細を表示することができます。
もし間違った情報を記録してしまった場合、このページで編集することができます。

###QRコード読み取りページ
QRコードを読み取ることができます。
出店団体の指定のQRコードを読み取った場合、くじ引き機能を呼び出し、メインページに戻ります。

###くじ引き機能
この機能が呼び出されたときは、スロットが実行されます。
すべての目がそろったときは、ドローン体験、プリクラ撮影、VR体験などの体育館での展示の優先権が与えられます。

###口コミ表示ページ
ここでは、TwitterAPIを使用し、口コミを表示します。

