#!/bin/bash

echo "Applying migration whatIsDonatorsName"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsDonatorsName               controllers.whatIsDonatorsNameController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsDonatorsName               controllers.whatIsDonatorsNameController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changewhatIsDonatorsName                        controllers.whatIsDonatorsNameController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changewhatIsDonatorsName                        controllers.whatIsDonatorsNameController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsDonatorsName.title = whatIsDonatorsName" >> ../conf/messages.en
echo "whatIsDonatorsName.heading = whatIsDonatorsName" >> ../conf/messages.en
echo "whatIsDonatorsName.checkYourAnswersLabel = whatIsDonatorsName" >> ../conf/messages.en
echo "whatIsDonatorsName.error.nonNumeric = Please give an answer for whatIsDonatorsName using numbers" >> ../conf/messages.en
echo "whatIsDonatorsName.error.required = Please give an answer for whatIsDonatorsName" >> ../conf/messages.en
echo "whatIsDonatorsName.error.wholeNumber = Please give an answer for whatIsDonatorsName using whole numbers" >> ../conf/messages.en
echo "whatIsDonatorsName.error.outOfRange = whatIsDonatorsName must be between {0} and {1}" >> ../conf/messages.en

echo "Adding helper line into UserAnswers"
awk '/class/ {\
     print;\
     print "  def whatIsDonatorsName: Option[Int] = cacheMap.getEntry[Int](whatIsDonatorsNameId.toString)";\
     print "";\
     next }1' ../app/utils/UserAnswers.scala > tmp && mv tmp ../app/utils/UserAnswers.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class/ {\
     print;\
     print "";\
     print "  def whatIsDonatorsName: Option[AnswerRow] = userAnswers.whatIsDonatorsName map {";\
     print "    x => AnswerRow(\"whatIsDonatorsName.checkYourAnswersLabel\", s\"$x\", false, routes.whatIsDonatorsNameController.onPageLoad(CheckMode).url)";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration whatIsDonatorsName completed"
