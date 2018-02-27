#!/bin/bash

echo "Applying migration donatorsSurname"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /donatorsSurname               controllers.donatorsSurnameController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /donatorsSurname               controllers.donatorsSurnameController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changedonatorsSurname                        controllers.donatorsSurnameController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changedonatorsSurname                        controllers.donatorsSurnameController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "donatorsSurname.title = donatorsSurname" >> ../conf/messages.en
echo "donatorsSurname.heading = donatorsSurname" >> ../conf/messages.en
echo "donatorsSurname.checkYourAnswersLabel = donatorsSurname" >> ../conf/messages.en
echo "donatorsSurname.error.required = Enter donatorsSurname" >> ../conf/messages.en
echo "donatorsSurname.error.length = donatorsSurname must be 50 characters or less" >> ../conf/messages.en

echo "Adding helper line into UserAnswers"
awk '/class/ {\
     print;\
     print "  def donatorsSurname: Option[String] = cacheMap.getEntry[String](donatorsSurnameId.toString)";\
     print "";\
     next }1' ../app/utils/UserAnswers.scala > tmp && mv tmp ../app/utils/UserAnswers.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class/ {\
     print;\
     print "";\
     print "  def donatorsSurname: Option[AnswerRow] = userAnswers.donatorsSurname map {";\
     print "    x => AnswerRow(\"donatorsSurname.checkYourAnswersLabel\", s\"$x\", false, routes.donatorsSurnameController.onPageLoad(CheckMode).url)";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration donatorsSurname completed"
