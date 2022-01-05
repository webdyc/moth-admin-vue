<template>
  <div class="app-content">
    <a-button type="primary" @click="showModal"> 打开弹窗 </a-button>
    <a-modal
      title="可拖拽弹出框"
      v-model:visible="updateFormVisible"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
      @cancel="handleCancel"
    >
      <div class="table-modal-content" v-ant-drag-dialog>
        <p>{{ ModalText }}</p>
      </div>
    </a-modal>
  </div>
</template>

<script>
import { reactive, toRefs } from '@vue/reactivity'

export default {
  name: 'DragDialogDemo',
  setup() {
    const state = reactive({
      confirmLoading: false,
      updateFormVisible: false,
      ModalText: 123123213123,
      options: [
        { value: '选项1', label: '黄金糕' },
        { value: '选项2', label: '双皮奶' },
        { value: '选项3', label: '蚵仔煎' },
        { value: '选项4', label: '龙须面' }
      ],
      value: '',
      gridData: [
        {
          date: '2016-05-02',
          name: 'John Smith',
          address: 'No.1518,  Jinshajiang Road, Putuo District'
        },
        {
          date: '2016-05-04',
          name: 'John Smith',
          address: 'No.1518,  Jinshajiang Road, Putuo District'
        },
        {
          date: '2016-05-01',
          name: 'John Smith',
          address: 'No.1518,  Jinshajiang Road, Putuo District'
        },
        {
          date: '2016-05-03',
          name: 'John Smith',
          address: 'No.1518,  Jinshajiang Road, Putuo District'
        }
      ]
    })

    const showModal = () => {
      state.updateFormVisible = true
    }
    const handleOk = () => {
      state.updateFormVisible = true
    }

    const handleCancel = () => {}
    return {
      ...toRefs(state),
      showModal,
      handleOk,
      handleCancel
    }
  }
}
</script>
