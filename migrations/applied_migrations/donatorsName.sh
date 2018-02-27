#!/bin/bash

echo "Applying migration donatorsName"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /donatorsName               controllers.donatorsNameController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /donatorsName               controllers.donatorsNameController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changedonatorsName                        controllers.donatorsNameController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changedonatorsName                        controllers.donatorsNameController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "donatorsName.title = donatorsName" >> ../conf/messages.en
echo "donatorsName.heading = donatorsName" >> ../conf/messages.en
echo "donatorsName.checkYourAnswersLabel = donatorsName" >> ../conf/messages.en
echo "donatorsName.error.required = Enter donatorsName" >> ../conf/messages.en
echo "donatorsName.error.length = donatorsName must be 20 characters or less" >> ../conf/messages.en

echo "Adding helper line into UserAnswers"
awk '/class/ {\
     print;\
     print "  def donatorsName: Option[String] = cacheMap.getEntry[String](donatorsNameId.toString)";\
     print "";\
     next }1' ../app/utils/UserAnswers.scala > tmp && mv tmp ../app/utils/UserAnswers.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class/ {\
     print;\
     print "";\
     print "  def donatorsName: Option[AnswerRow] = userAnswers.donatorsName map {";\
     print "    x => AnswerRow(\"donatorsName.checkYourAnswersLabel\", s\"$x\", false, routes.donatorsNameController.onPageLoad(CheckMode).url)";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration donatorsName completed"
