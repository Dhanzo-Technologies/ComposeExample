# Function to run Detekt checks
run_detekt_checks() {
    echo "\n🚀 Detekt is now analyzing your Kotlin code for potential issues!"
    ./gradlew detekt > /tmp/detekt-result
    DETEKT_EXIT_CODE=$?

    if [ ${DETEKT_EXIT_CODE} -ne 0 ]; then
        cat /tmp/detekt-result
        rm /tmp/detekt-result
        echo "\n*********************************************************************************"
        echo "     💥 Oh no! Detekt found issues in the code! Time to fix those issues! 💥"
        echo "     💡 Tip: Review the Detekt report to resolve these issues. 🛠️"
        echo "*********************************************************************************"
        exit ${DETEKT_EXIT_CODE}
    else
        rm /tmp/detekt-result
        echo "🎉 Fantastic work! Your Kotlin code has sailed through Detekt's analysis with ease! Onward to greatness! 🚀🌟"
    fi
}

# Function to print success message
print_success_message() {
    GIT_USERNAME=$(git config user.name)
    echo "\n *******************************************************************************"
    echo "🚀🎉 Huzzah, $GIT_USERNAME! Your code has triumphed through the Style Checker Dragon unscathed! 🐉"
    echo "Your code shines brighter than a supernova and sparkles like a constellation of stars! ✨🌌"
    echo "*******************************************************************************"
    echo "\n🚀🎉 Hold tight, $GIT_USERNAME! Your code is ready to commit and conquer new heights! 🌟✨ Keep up the amazing work! 💪\n"
}

run_detekt_checks
print_success_message

exit 0