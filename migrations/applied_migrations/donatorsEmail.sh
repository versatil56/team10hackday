#!/bin/bash

echo "Applying migration donatorsEmail"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /donatorsEmail               controllers.donatorsEmailController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /donatorsEmail               controllers.donatorsEmailController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changedonatorsEmail                        controllers.donatorsEmailController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changedonatorsEmail                        controllers.donatorsEmailController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "donatorsEmail.title = donatorsEmail" >> ../conf/messages.en
echo "donatorsEmail.heading = donatorsEmail" >> ../conf/messages.en
echo "donatorsEmail.checkYourAnswersLabel = donatorsEmail" >> ../conf/messages.en
echo "donatorsEmail.error.required = Enter donatorsEmail" >> ../conf/messages.en
echo "donatorsEmail.error.length = donatorsEmail must be 100 characters or less" >> ../conf/messages.en

echo "Adding helper line into UserAnswers"
awk '/class/ {\
     print;\
     print "  def donatorsEmail: Option[String] = cacheMap.getEntry[String](donatorsEmailId.toString)";\
     print "";\
     next }1' ../app/utils/UserAnswers.scala > tmp && mv tmp ../app/utils/UserAnswers.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class/ {\
     print;\
     print "";\
     print "  def donatorsEmail: Option[AnswerRow] = userAnswers.donatorsEmail map {";\
     print "    x => AnswerRow(\"donatorsEmail.checkYourAnswersLabel\", s\"$x\", false, routes.donatorsEmailController.onPageLoad(CheckMode).url)";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration donatorsEmail completed"
