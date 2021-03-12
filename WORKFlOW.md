## 1. Create a branch
When you're working on a project, you're going to have a bunch of different features or ideas in progress
at any given time – some of which are ready to go, and others which are not. Branching exists to help you
manage this workflow.

When you create a branch in your project, you're creating an environment where you can try out new ideas. 
Changes you make on a branch don't affect the master branch, so you're free to experiment and commit changes,
safe in the knowledge that your branch won't be merged until it's ready to be reviewed by someone you're 
collaborating with.

	Sidenote: In order for code to be approved and merged, please add 2 peers to code review.

---

## 2. Add commits
Once your branch has been created, it's time to start making changes. Whenever you add, edit, or delete a 
file, you're making a commit, and adding them to your branch. This process of adding commits keeps track of
your progress as you work on a feature branch.

Commits also create a transparent history of your work that others can follow to understand what you've 
done and why. Each commit has an associated commit message, which is a description explaining why a 
particular change was made. Furthermore, each commit is considered a separate unit of change. This lets you
roll back changes if a bug is found, or if you decide to head in a different direction.

	Sidenote: Very important to make regular commits to help peers in the review process.

---

## 3. Open a Pull Request
Pull Requests initiate discussion about your commits. Because they're tightly integrated with the 
underlying Git repository, anyone can see exactly what changes would be merged if they accept your request.

You can open a Pull Request at any point during the development process: when you have little or no code 
but want to share some screenshots or general ideas, when you're stuck and need help or advice, or when 
you're ready for someone to review your work. By using GitHub's @mention system in your Pull Request 
message, you can ask for feedback from specific people or teams, whether they're down the hall or ten time 
zones away.

	Sidenote: Make a draft pull request if you think your code is not ready to be merged, but still
		  want feedback.

---

## 4. Discuss and review your code
Once a Pull Request has been opened, the person or team reviewing your changes may have questions or 
comments. Perhaps the coding style doesn't match project guidelines, or maybe everything looks great and 
props are in order. Pull Requests are designed to encourage and capture this type of conversation.

You can also continue to push to your branch in light of discussion and feedback about your commits. If 
someone comments that you forgot to do something or if there is a bug in the code, you can fix it in your 
branch and push up the change. GitHub will show your new commits and any additional feedback you may 
receive in the unified Pull Request viewD.

---

## 5. Deploy

With GitHub, you can deploy from a branch for final testing in production before merging to main.

Once your pull request has been reviewed and the branch passes your tests, you can deploy your changes to 
verify them in production. If your branch causes issues, you can roll it back by deploying the existing 
main branch into production.

Different teams may have different deployment strategies. For some, it may be best to deploy to a specially 
provisioned testing environment. For others, deploying directly to production may be the better choice 
based on the other elements in their workflow.

## 6. Merge
Now that your changes have been verified in production, it is time to merge your code into the master 
branch.

Once merged, Pull Requests preserve a record of the historical changes to your code. Because they're 
searchable, they let anyone go back in time to understand why and how a decision was made.