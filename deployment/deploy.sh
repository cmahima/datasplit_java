

java -jar $1 \
--debug.logging.enabled false \
--file.location /Users/mahima.chaudhary/IdeaProjects/datasplit/src/main/resources/data/iris.txt \
--train.test.percentage 0.8 \
--train.output.location /Users/mahima.chaudhary/IdeaProjects/datasplit/src/main/resources/data/irisTrain.txt \
--test.output.location /Users/mahima.chaudhary/IdeaProjects/datasplit/src/main/resources/data/irisTest.txt \
--aws.s3.bucket.name java-boot-camp \
--aws.s3.input.file.name datasplit/iris.txt \
--aws.access.key.id  \
--aws.s3.secrets.key