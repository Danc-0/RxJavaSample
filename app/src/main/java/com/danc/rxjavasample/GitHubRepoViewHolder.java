package com.danc.rxjavasample;

import android.view.View;
import android.widget.TextView;

public class GitHubRepoViewHolder {

    private TextView textRepoName;
    private TextView textRepoDescription;
    private TextView textLanguage;
    private TextView textStars;

    public GitHubRepoViewHolder(View view) {
        textRepoName = (TextView) view.findViewById(R.id.text_repo_name);
        textRepoDescription = (TextView) view.findViewById(R.id.text_repo_description);
        textLanguage = (TextView) view.findViewById(R.id.text_language);
        textStars = (TextView) view.findViewById(R.id.text_stars);
    }

    public void setGitHubRepo(GitHubRepo gitHubRepo) {
        textRepoName.setText(gitHubRepo.name);
        textRepoDescription.setText(gitHubRepo.description);
        textLanguage.setText("Language: " + gitHubRepo.language);
        textStars.setText("Stars: " + gitHubRepo.stargazersCount);
    }
}

