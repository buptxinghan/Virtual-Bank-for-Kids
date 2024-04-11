package softwareEngineering.VirtualBankV1.src.main.java.com.virtualbankv1;

import java.util.List;
import java.util.ArrayList;

public class VirtualBankApplication {
    protected List<Account> accounts; // 存储所有用户账户的信息
    protected String currentUser; // 表示当前登录系统的用户
    protected Account currentAccount; // 表示当前进入的用户账户
    protected List<Transaction> transactions; // 存储所有账户的交易记录
    protected List<Task> tasks; // 存储所有设置的任务
    protected List<Goal> goals; // 存储所有用户设定的长期目标
    protected SupportSystem support; // 提供用户支持和帮助信息

    public VirtualBankApplication() {
        accounts = new ArrayList<>();
        transactions = new ArrayList<>();
        tasks = new ArrayList<>();
        goals = new ArrayList<>();
        support = new SupportSystem();
    }

    // ... 其他必要的方法

    // 主函数，用于测试
    public static void main(String[] args) {
        VirtualBankApplication application = new VirtualBankApplication();
        // 测试系统功能
    }
}
