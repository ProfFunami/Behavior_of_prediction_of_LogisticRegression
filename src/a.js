w = 1;
h = 100; //波の高さ
step = 5;
startP = 0; //開始角度
endP = 360; //終了角度(1周期360、2周期なら720・・・。)
passData = [];
count = 0;
for (i=startP; i<=endP; i+=step){
    rad = i * Math.PI / 180; //度からラジアンへの変換
    y = Math.sin(rad) * h;
    x = i * w;
    passData[count++]= [x, y];
}
docObj = activeDocument;
pObj = docObj.pathItems.add();
pObj.setEntirePath(passData);
pObj.filled = false;
pObj.stroked = true;
pObj.strokeWidth = 3;