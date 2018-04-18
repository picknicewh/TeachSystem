package com.mayi.yun.teachsystem.ui.study;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseFragment;
import com.mayi.yun.teachsystem.bean.StudyVo;
import com.mayi.yun.teachsystem.ui.study.detail.StudySysDetailActivity;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2018/2/28
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StudySystemFragment extends BaseFragment<StudySystemPresenter> implements StudySystemContract.View, OnItemClickListener {

    private static StudySystemFragment knowledgeSystemFragment;
    @BindView(R.id.rv_system)
    RecyclerView rvSystem;
    /**
     * 适配器
     */
    private StudySystemAdapter adapter;
    /**
     * 数据列表
     */
    private List<StudyVo> knowledgeSystemList;

    public static StudySystemFragment newInstance() {
        if (knowledgeSystemFragment == null) {
            knowledgeSystemFragment = new StudySystemFragment();
        }
        return knowledgeSystemFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_study_system;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        knowledgeSystemList = new ArrayList<>();
        rvSystem.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StudySystemAdapter(knowledgeSystemList);
        rvSystem.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        mPresenter.getKnowledgeSystemList();
    }

    @Override
    public void setKnowledgeSystemList(List<StudyVo> knowledgeSystemList) {
        this.knowledgeSystemList.clear();
        this.knowledgeSystemList.addAll(knowledgeSystemList);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(getActivity(), StudySysDetailActivity.class);
        intent.putExtra("title",knowledgeSystemList.get(position).getName());
        intent.putStringArrayListExtra("titles",getTitleList(position));
        intent.putIntegerArrayListExtra("cids",getCidList(position));
        startActivity(intent);
    }

    public ArrayList<String> getTitleList(int position){
        ArrayList<String> titles = new ArrayList<>();
        StudyVo knowledgeSystem = knowledgeSystemList.get(position);
        for (int i = 0 ;i<knowledgeSystem.getChildren().size();i++){
            StudyVo.Children children = knowledgeSystem.getChildren().get(i);
            titles.add(children.getName());
        }
        return titles;
    }
    public ArrayList<Integer> getCidList(int position){
        ArrayList<Integer> cids = new ArrayList<>();
        StudyVo knowledgeSystem = knowledgeSystemList.get(position);
        for (int i = 0 ;i<knowledgeSystem.getChildren().size();i++){
            StudyVo.Children children = knowledgeSystem.getChildren().get(i);
            cids.add(children.getId());
        }
        return cids;
    }
}
