

java -jar $1 \
--debug.logging.enabled false \
--file.location /home/sanket/Documents/workspace/datasplit_java/src/main/resources/data/iris.txt \
--train.test.percentage 0.8 \
--train.output.location /home/sanket/Documents/workspace/datasplit_java/src/main/resources/data/irisTrain.txt \
--test.output.location /home/sanket/Documents/workspace/datasplit_java/src/main/resources/data/irisTest.txt