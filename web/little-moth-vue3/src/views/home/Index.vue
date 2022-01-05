<template>
  <!-- 首页 -->
  <a-row :gutter="[16, 0]" class="account">
    <a-col :span="7">
      <!-- 用户信息卡片 -->
      <a-card class="account-center">
        <!-- 用户信息 - 头部 -->
        <div class="account-center-avatarHolder">
          <div class="avatar">
            <a-image
              src="https://www.antdv.com/#error"
              fallback="data:image/png;base64,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"
            />
          </div>
          <div class="username">webdyc</div>
          <div class="bio">求知若愚，求贤若饥</div>
        </div>
        <!-- 用户信息 - 详情 -->
        <div class="account-center-detail">
          <p><radar-chart-outlined />web前端</p>
          <p><cluster-outlined />技术部</p>
          <p><qq-outlined />123456</p>
        </div>
        <!-- 分割线 -->
        <a-divider />
        <!-- 标签 -->
        <div class="account-center-tags">
          <div class="tagsTitle">标签</div>
          <div class="mt-1">
            <template v-for="(tag, index) in tags" :key="tag">
              <a-tooltip v-if="tag.length > 20" :title="tag">
                <a-tag :closable="index >= 5" @close="handleClose(tag)">
                  {{ `${tag.slice(0, 20)}...` }}
                </a-tag>
              </a-tooltip>
              <a-tag v-else :closable="index >= 5" @close="handleClose(tag)">
                {{ tag }}
              </a-tag>
            </template>
            <a-input
              v-if="inputVisible"
              ref="inputRef"
              v-model:value="inputValue"
              type="text"
              size="small"
              :style="{ width: '78px' }"
              @blur="handleInputConfirm"
              @keyup.enter="handleInputConfirm"
            />
            <a-tag
              v-else
              style="background: #fff; border-style: dashed"
              @click="showInput"
            >
              <plus-outlined />
              New Tag
            </a-tag>
          </div>
        </div>
      </a-card>
    </a-col>
    <a-col :span="17">
      <!-- 用户信息卡片 -->
      <a-card class="tabs-center">
        <a-tabs v-model:activeKey="activeKey">
          <a-tab-pane key="1" tab="文章"> Content of Tab Pane 1 </a-tab-pane>
          <a-tab-pane key="2" tab="内容" force-render>
            Content of Tab Pane 2
          </a-tab-pane>
          <a-tab-pane key="3" tab="项目"> Content of Tab Pane 3 </a-tab-pane>
        </a-tabs>
      </a-card>
    </a-col>
  </a-row>
</template>
<script>
import {
  RadarChartOutlined,
  ClusterOutlined,
  QqOutlined
} from '@ant-design/icons-vue'
import { ref, reactive, toRefs, nextTick } from 'vue'
import { PlusOutlined } from '@ant-design/icons-vue'

export default {
  name: 'Home',
  components: {
    RadarChartOutlined,
    ClusterOutlined,
    QqOutlined,
    PlusOutlined
  },
  setup() {
    const inputRef = ref()
    const state = reactive({
      activeKey: '1',
      tags: ['HTML', 'CSS', 'JavaScript', 'Vue', 'React'],
      inputVisible: false,
      inputValue: ''
    })

    const handleClose = (removedTag) => {
      const tags = state.tags.filter((tag) => tag !== removedTag)
      console.log(tags)
      state.tags = tags
    }

    const showInput = () => {
      state.inputVisible = true
      nextTick(() => {
        inputRef.value.focus()
      })
    }

    const handleInputConfirm = () => {
      const inputValue = state.inputValue
      let tags = state.tags

      if (inputValue && tags.indexOf(inputValue) === -1) {
        tags = [...tags, inputValue]
      }
      Object.assign(state, {
        tags,
        inputVisible: false,
        inputValue: ''
      })
    }

    return {
      ...toRefs(state),
      handleClose,
      showInput,
      handleInputConfirm,
      inputRef
    }
  }
}
</script>
<style lang="scss" scoped>
.account {
  .account-center {
    .account-center-avatarHolder {
      margin-bottom: 24px;
      text-align: center;
      .avatar {
        width: 104px;
        height: 104px;
        margin: 0 auto;
        margin-bottom: 20px;
        overflow: hidden;
        border-radius: 50%;
        img {
          width: 100%;
          height: 100%;
        }
      }
      .username {
        margin-bottom: 4px;
        color: rgba(0, 0, 0, 0.85);
        font-weight: 500;
        font-size: 20px;
        line-height: 28px;
      }
    }
    .account-center-detail {
      p {
        position: relative;
        margin-bottom: 8px;
        padding-left: 26px;
        .anticon {
          position: absolute;
          top: 4px;
          left: 0;
          width: 14px;
          height: 14px;
        }
      }
    }
    .account-center-tags {
      .ant-tag {
        margin-bottom: 8px;
      }
    }
  }
  .tabs-center {
    .ant-card-body {
      padding: 0 !important;
    }
  }
}
</style>
